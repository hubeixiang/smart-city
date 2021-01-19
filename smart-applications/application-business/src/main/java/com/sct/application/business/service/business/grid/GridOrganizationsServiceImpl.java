package com.sct.application.business.service.business.grid;

import com.sct.commons.utils.id.IntIdGenerator;
import com.sct.service.core.FormatDataServiceImpl;
import com.sct.service.database.entity.ScOrganization;
import com.sct.service.main.ScOrganizationImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridOrganizationsServiceImpl {

    @Autowired
    private ScOrganizationImpl scOrganizationImpl;

    @Autowired
    private FormatDataServiceImpl formatDataServiceImpl;

    public ScOrganization create(ScOrganization scOrganization) {
        //新增组织数据时,需要填补相关的属性信息
        fillCreateInfo(scOrganization);
        return scOrganizationImpl.insert(scOrganization);
    }

    public int delete(Integer id) {
        return scOrganizationImpl.delete(id);
    }

    public int delete(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return scOrganizationImpl.deletes(ids);
    }

    public int update(ScOrganization scOrganization) {
        //更新组织信息前,需要填补相关的属性信息
        fillUpdateInfo(scOrganization);
        return scOrganizationImpl.update(scOrganization);
    }

    public int cancel(Integer id) {
        return scOrganizationImpl.cancel(id);
    }

    private void fillCreateInfo(ScOrganization scOrganization) {
        String idstr = String.format("%s:%s", scOrganization.getOrgType(), scOrganization.getName());
        scOrganization.setId(IntIdGenerator.getCRC32(idstr));
    }

    private void fillUpdateInfo(ScOrganization scOrganization) {

    }
}
