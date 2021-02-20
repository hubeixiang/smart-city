package com.sct.service.main;

import com.sct.service.database.entity.ScCommunityParty;
import com.sct.service.database.mapper.ScCommunityPartyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScCommunityPartyImpl {

    @Autowired
    private ScCommunityPartyMapper scCommunityPartyMapper;

    public ScCommunityParty insert(ScCommunityParty scCommunityParty) {
        scCommunityPartyMapper.insert(scCommunityParty);
        return scCommunityParty;
    }

    public int delete(Integer id) {

        return scCommunityPartyMapper.deleteByPrimaryKey(id);
    }

    public int deleteByCommunityId(Integer id) {
        return scCommunityPartyMapper.deleteByCommunityId(id);
    }

    public int deleteByCommunityIds(List<Integer> ids) {
        return scCommunityPartyMapper.deleteByCommunityIds(ids);
    }

    public int deletes(List<Integer> ids) {
        return scCommunityPartyMapper.deleteByPrimaryKeys(ids);
    }

    public int update(ScCommunityParty scCommunityParty) {
        return scCommunityPartyMapper.updateByPrimaryKey(scCommunityParty);
    }

    public ScCommunityParty selectByCommunityId(Integer id) {
        return scCommunityPartyMapper.selectByCommunityId(id);
    }
}
