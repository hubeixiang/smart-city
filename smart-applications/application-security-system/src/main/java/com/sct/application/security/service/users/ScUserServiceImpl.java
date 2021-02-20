package com.sct.application.security.service.users;

import com.sct.commons.file.location.FileLocation;
import com.sct.commons.i18n.I18nMessageUtil;
import com.sct.commons.utils.id.IDGenerator;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.exception.ResourceNotFoundException;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScOrganizationConditionExport;
import com.sct.service.database.condition.ScUserCondition;
import com.sct.service.database.condition.ScUserConditionExport;
import com.sct.service.database.entity.ScResident;
import com.sct.service.database.entity.ScUser;
import com.sct.service.database.mapper.ScResidentMapper;
import com.sct.service.database.mapper.ScUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScUserServiceImpl {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ScUserMapper scUserMapper;

    @Autowired
    private ScResidentMapper scResidentMapper;

    @Autowired
    private FormatDataServiceImpl formatDataService;

    public ResultVOEntity list(ScUserCondition condition) {
        List data = scUserMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScUserCondition condition) {
        int totalSize = scUserMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scUserMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    public FileLocation export2FileLocation(ScUserConditionExport conditionExport) {
        return formatDataService.export2FileLocation(conditionExport.getExportCondition(),
                () -> scUserMapper.selectCondition(conditionExport.getCondition()));
    }

    @Transactional
    public ScUser create(ScUser scUser, boolean isScResident) {
        //新创建的用户,需要先查找居民表中是否存在,如果居民中存在
        Integer id = null;
        if (isScResident) {
            String cardId = scUser.getCardId();
            if (StringUtils.isEmpty(cardId)) {
                throw APIException.of(ExceptionCode.SERVER_API_PARAMETER_ERROR, "Require cardId");
            }
            ScResident scResident = scResidentMapper.selectByCardId(cardId);
            if (scResident == null) {
                throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, I18nMessageUtil.getInstance().getMessage("ScUserServiceImpl.create.card_id_not_exists"));
            } else {
                id = scResident.getId();
                scUser.setCardId(scResident.getCardId());
                ScUser user = scUserMapper.selectByCardId(scResident.getCardId());
                if (user != null) {
                    throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, I18nMessageUtil.getInstance().getMessage("ScUserServiceImpl.create.card_id_already_exists"));
                }
            }
        } else {
            //将信息创建到数据库
            String uuid = IDGenerator.UUID.generate();
            id = UserIdGenerator.crc32Id("builtin", uuid);
            scUser.setId(id);
        }
        final String rawPassword = scUser.getPassword();
        //插入数据
        scUserMapper.insert(scUser);
        //更新用户密码
        if (StringUtils.isNoneBlank(rawPassword)) {
            if (!updatePassword(id, rawPassword, null)) {
                throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "Password update error.", null);
            }
        }
        return scUserMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int delete(Integer id) {
        return scUserMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public int delete(List<Integer> ids) {
        int size = 0;
        for (Integer id : ids) {
            int d = delete(id);
            if (d == 1) {
                size++;
            }
        }
        return size;
    }

    public ScUser findScUserById(Integer id) {
        return scUserMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public ScUser update(ScUser scUser, boolean all) {
        if (scUser == null || scUser.getId() == null) {
            throw new IllegalArgumentException();
        }
        final Integer id = scUser.getId();

        int size = scUserMapper.updateByPrimaryKey(scUser);
        if (size == 1) {
            return scUserMapper.selectByPrimaryKey(id);
        } else {
            throw ResourceNotFoundException.of(id);
        }
    }

    @Transactional
    public boolean updatePassword(final Integer id, final String rawPassword, final String oldPassword) {
        if (oldPassword != null && oldPassword.length() != 0) {
            ScUser scUser = scUserMapper.selectByPrimaryKey(id);
            if (scUser == null) {
                throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "Resource not found.", null);
            }
            if (oldPassword.equals(rawPassword)) {
                throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "Password is same as original password.", null);
            }
            if (!passwordEncoder.matches(oldPassword, scUser.getPassword())) {
                throw APIException.of(ExceptionCode.SERVER_API_PARAMETER_ERROR, "Old password does not match.", null);
            }
        }

        final String password = passwordEncoder.encode(rawPassword);
        int ok = scUserMapper.updatePasswordById(id, password);
        if (ok == 1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 检查相关注册信息是否已经存在
     *
     * @param scUser
     */
    public void checkScUserExists(ScUser scUser) {
        Map<String, String> map = new HashMap<>();
        //1. 检查微信号
        if (StringUtils.isNotEmpty(scUser.getWxId())) {
            ScUser user = scUserMapper.selectBywxid(scUser.getWxId());
            if (user != null) {
                map.put("wxId", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_wxid", scUser.getWxId()));
            }
        }
        //2. 检查userId
        if (StringUtils.isNotEmpty(scUser.getUserId())) {
            ScUser user = scUserMapper.selectByLoginId(scUser.getUserId());
            if (user != null) {
                map.put("userId", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_userid", scUser.getUserId()));
            }
        }
        //3. 检查mobile
        if (StringUtils.isNotEmpty(scUser.getMobile())) {
            ScUser user = scUserMapper.selectByMobile(scUser.getMobile());
            if (user != null) {
                map.put("mobile", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_mobile", scUser.getMobile()));
            }
        }
        //4. 检查email
        if (StringUtils.isNotEmpty(scUser.getEmail())) {
            ScUser user = scUserMapper.selectByEmail(scUser.getEmail());
            if (user != null) {
                map.put("email", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_email", scUser.getEmail()));
            }
        }
        //5. 检查cardId
        if (StringUtils.isNotEmpty(scUser.getCardId())) {
            ScUser user = scUserMapper.selectByCardId(scUser.getCardId());
            if (user != null) {
                map.put("carId", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_cardid", scUser.getCardId()));
            }
        }
        if (map.size() > 0) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, map);
        }
    }

}
