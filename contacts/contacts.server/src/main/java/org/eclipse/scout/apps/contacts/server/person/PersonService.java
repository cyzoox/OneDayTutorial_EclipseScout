package org.eclipse.scout.apps.contacts.server.person;

import org.eclipse.scout.apps.contacts.server.sql.SQLs;
import org.eclipse.scout.apps.contacts.shared.person.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.UUID;

public class PersonService implements IPersonService {
    @Override
    public PersonTablePageData getPersonTableData(SearchFilter filter) {
        PersonTablePageData pageData = new PersonTablePageData();

        String sql = SQLs.PERSON_PAGE_SELECT + SQLs.PERSON_PAGE_DATA_SELECT_INTO;
        SQL.selectInto(sql, new NVPair("page", pageData));

        return pageData;
    }

    @Override
    public PersonFormData prepareCreate(PersonFormData formData) {
        if (!ACCESS.check(new CreatePersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [clintjamesrpascual] add business logic here.
        return formData;
    }


    @Override
    public PersonFormData create(PersonFormData formData) {
        if (!ACCESS.check(new CreatePersonPermission())) {
            throw new VetoException(TEXTS.get("InsufficientPrivileges"));
        }

        // add a unique person id if necessary
        if (StringUtility.isNullOrEmpty(formData.getPersonId())) {
            formData.setPersonId(UUID.randomUUID().toString());
        }

        SQL.insert(SQLs.PERSON_INSERT, formData);

        return store(formData);
    }

    @Override
    public PersonFormData load(PersonFormData formData) {
        if (!ACCESS.check(new ReadPersonPermission())) {
            throw new VetoException(TEXTS.get("InsufficientPrivileges"));
        }

        SQL.selectInto(SQLs.PERSON_SELECT, formData);

        return formData;
    }

    @Override
    public PersonFormData store(PersonFormData formData) {
        if (!ACCESS.check(new UpdatePersonPermission())) {
            throw new VetoException(TEXTS.get("InsufficientPrivileges"));
        }

        SQL.update(SQLs.PERSON_UPDATE, formData);

        return formData;
    }
}
