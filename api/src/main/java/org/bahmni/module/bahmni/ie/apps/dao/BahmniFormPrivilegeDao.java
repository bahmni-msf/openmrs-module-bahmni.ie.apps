package org.bahmni.module.bahmni.ie.apps.dao;

import org.bahmni.module.bahmni.ie.apps.model.BahmniFormPrivilege;
import org.openmrs.api.db.DAOException;
import java.util.List;

public interface BahmniFormPrivilegeDao {

    List<BahmniFormPrivilege> getAllPrivilegesForForm(Integer formId) throws DAOException;

    BahmniFormPrivilege saveFormPrivilege(BahmniFormPrivilege formPrivilege) throws DAOException;

    BahmniFormPrivilege getFormPrivilege(String privilegeName , Integer formId) throws DAOException;

    void deleteFormPrivilege(BahmniFormPrivilege formPrivilege) throws DAOException;
}
