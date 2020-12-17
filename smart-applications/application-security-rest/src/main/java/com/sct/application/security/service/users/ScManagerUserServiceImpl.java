package com.sct.application.security.service.users;

import com.sct.commons.i18n.I18nMessageUtil;
import com.sct.commons.utils.id.IDGenerator;
import com.sct.service.core.service.QPagingUtil;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.exception.ResourceNotFoundException;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;
import com.sct.service.database.condition.QPaging;
import com.sct.service.database.condition.ScUserManagerCondition;
import com.sct.service.database.entity.ScManager;
import com.sct.service.database.entity.ScUser;
import com.sct.service.database.mapper.ScManagerMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScManagerUserServiceImpl {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ScManagerMapper scManagerMapper;

    public ResultVOEntity list(ScUserManagerCondition condition) {
        List data = scManagerMapper.selectCondition(condition);
        List<String> columns = QPagingUtil.parserResultColumns(data);
        return ResultVOEntity.of(columns, data);
    }

    public PageResultVO listPage(Paging paging, ScUserManagerCondition condition) {
        int totalSize = scManagerMapper.selectConditionCount(condition);
        PageResponse pageResponse = PageResponse.of(paging.getPageIndex(), paging.getPageSize(), totalSize);
        ResultVOEntity resultVO = null;
        if (totalSize == 0) {
            resultVO = ResultVOEntity.of();
        } else {
            QPaging qPaging = QPagingUtil.toQPaging(paging);
            if (totalSize < qPaging.getEndIndex()) {
                qPaging.setEndIndex(totalSize);
            }
            List data = scManagerMapper.selectConditionPage(condition, qPaging);
            List<String> columns = QPagingUtil.parserResultColumns(data);
            resultVO = ResultVOEntity.of(columns, data);
        }

        return PageResultVO.of(pageResponse, resultVO);
    }

    @Transactional
    public ScManager create(ScManager scManager) {
        //将信息创建到数据库
        String id = IDGenerator.UUID.generate();
        scManager.setId(id);
        final String rawPassword = scManager.getPassword();
        //插入数据
        scManagerMapper.insert(scManager);
        //更新用户密码
        if (StringUtils.isNoneBlank(rawPassword)) {
            if (!updatePassword(id, rawPassword, null)) {
                throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "Password update error.", null);
            }
        }
        return scManagerMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int delete(String id) {
        return scManagerMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public int delete(List<String> ids) {
        int size = scManagerMapper.deleteByPrimaryKeys(ids);
        if (size != ids.size()) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "Delete The number of resources is not equal.", ids);
        }
        return size;
    }

    public ScManager findScUserById(String id) {
        return scManagerMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public ScManager update(ScManager scManager, boolean all) {
        if (scManager == null || scManager.getId() == null) {
            throw new IllegalArgumentException();
        }
        final String id = scManager.getId();

        int size = scManagerMapper.updateByPrimaryKey(scManager);
        if (size == 1) {
            return scManagerMapper.selectByPrimaryKey(id);
        } else {
            throw ResourceNotFoundException.of(id);
        }
    }

    @Transactional
    public boolean updatePassword(final String id, final String rawPassword, final String oldPassword) {
        if (oldPassword != null && oldPassword.length() != 0) {
            ScManager scManager = scManagerMapper.selectByPrimaryKey(id);
            if (scManager == null) {
                throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "Resource not found.", null);
            }
            if (oldPassword.equals(rawPassword)) {
                throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "Password is same as original password.", null);
            }
        }

        final String password = passwordEncoder.encode(rawPassword);
        int ok = scManagerMapper.updatePasswordById(id, password);
        if (ok == 1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 检查相关注册信息是否已经存在
     *
     * @param scManager
     */
    public void checkScUserExists(ScManager scManager) {
        Map<String, String> map = new HashMap<>();
        //1. 检查微信号
        if (StringUtils.isNotEmpty(scManager.getWxId())) {
            ScManager user = scManagerMapper.selectBywxid(scManager.getWxId());
            if (user != null) {
                map.put("wxId", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_wxid", scManager.getWxId()));
            }
        }
        //2. 检查userId
        if (StringUtils.isNotEmpty(scManager.getUserId())) {
            ScManager user = scManagerMapper.selectByLoginId(scManager.getUserId());
            if (user != null) {
                map.put("userId", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_userid", scManager.getUserId()));
            }
        }
        //3. 检查mobile
        if (StringUtils.isNotEmpty(scManager.getMobile())) {
            ScManager user = scManagerMapper.selectByMobile(scManager.getMobile());
            if (user != null) {
                map.put("mobile", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_mobile", scManager.getMobile()));
            }
        }
        //4. 检查email
        if (StringUtils.isNotEmpty(scManager.getEmail())) {
            ScManager user = scManagerMapper.selectByEmail(scManager.getEmail());
            if (user != null) {
                map.put("email", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_email", scManager.getEmail()));
            }
        }
        //5. 检查cardId
        if (StringUtils.isNotEmpty(scManager.getCardId())) {
            ScManager user = scManagerMapper.selectByCardId(scManager.getCardId());
            if (user != null) {
                map.put("carId", I18nMessageUtil.getInstance().getMessage("Controller.Users.Operation.already_exists_cardid", scManager.getCardId()));
            }
        }
        if (map.size() > 0) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, map);
        }
    }
}
