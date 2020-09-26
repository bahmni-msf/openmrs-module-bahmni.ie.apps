package org.bahmni.module.bahmni.ie.apps.dao.impl;

import org.apache.commons.lang3.StringUtils;

import org.bahmni.module.bahmni.ie.apps.dao.BahmniFormPrivilegeDao;
import org.bahmni.module.bahmni.ie.apps.model.BahmniFormPrivilege;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import org.openmrs.api.db.DAOException;
import org.bahmni.module.bahmni.ie.apps.dao.BahmniFormDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class BahmniFormPrivilegeDaoImpl implements BahmniFormPrivilegeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public BahmniFormPrivilegeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<BahmniFormPrivilege> getAllPrivilegesForForm(Integer formId) throws DAOException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BahmniFormPrivilege.class);
        criteria.add(Restrictions.eq("formId", formId));
        return criteria.list();
    }
    @Override
    public BahmniFormPrivilege getFormPrivilege(String formPrivilegeName,Integer formId) throws DAOException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BahmniFormPrivilege.class);
        criteria.add(Restrictions.eq("formId", formId));
        criteria.add(Restrictions.eq("privilegeName",formPrivilegeName));
        return (BahmniFormPrivilege) criteria.uniqueResult();
    }

    @Transactional
    @Override
    public BahmniFormPrivilege saveFormPrivilege(BahmniFormPrivilege formPrivilege) {
        System.out.println("Inside BahmniFormPrivilegeDaoImpl &&&&&&-->saveFormPrivilege before save"+formPrivilege);
        sessionFactory.getCurrentSession().saveOrUpdate(formPrivilege);
        System.out.println("Inside BahmniFormPrivilegeDaoImpl &&&&&&-->saveFormPrivilege after save"+formPrivilege);
        return formPrivilege;
    }

    @Transactional
    @Override
    public void deleteFormPrivilege(BahmniFormPrivilege formPrivilege) {

//        Query query = sessionFactory.getCurrentSession().createQuery("delete from bahmni_form_privilege where form_id = :ID");
//        query.setParameter("ID", formId);
//        return query.executeUpdate();
         sessionFactory.getCurrentSession().delete(formPrivilege);
         sessionFactory.getCurrentSession().flush();
    }

}