package com.sct.commons.db.autoconfigure.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class CommonSqlSessionDaoSupport extends SqlSessionDaoSupport {
    private SqlSession sqlSessionBatch = null;

    public CommonSqlSessionDaoSupport(SqlSessionFactory sqlSessionFactory) {
        setSqlSessionFactory(sqlSessionFactory);
        initBatchSqlSession();
    }

    private void initBatchSqlSession() {
        sqlSessionBatch = new SqlSessionTemplate(
                ((SqlSessionTemplate) this.getSqlSession()).getSqlSessionFactory(),
                ExecutorType.BATCH);
    }

    /**
     * 获取的默认批量操作的sqlSession,对数据库做出了变更后,需要调用对应的commit方法,进行提交.数据库修改才会生效
     *
     * @return
     */
    public SqlSession getSqlSessionBatch() {
        return sqlSessionBatch;
    }
}
