package org.bahmni.module.bahmni.ie.apps.service;
import org.bahmni.module.bahmni.ie.apps.model.BahmniFormPrivilege;
import org.openmrs.api.OpenmrsService;
import java.util.List;

public interface BahmniFormPrivilegesService extends OpenmrsService {

    List<BahmniFormPrivilege> saveFormPrivileges(List<BahmniFormPrivilege> formPrivileges);

    List<BahmniFormPrivilege> getAllPrivilegesForForm(Integer formId);

    List<BahmniFormPrivilege> deleteAllPrivilegesForGivenFormId(Integer formId);

}
