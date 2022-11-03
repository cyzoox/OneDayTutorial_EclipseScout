package org.eclipse.scout.apps.contacts.server.person;

import org.eclipse.scout.apps.contacts.server.sql.SQLs;
import org.eclipse.scout.apps.contacts.shared.person.IPersonService;
import org.eclipse.scout.apps.contacts.shared.person.PersonTablePageData;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class PersonService implements IPersonService {
    @Override
    public PersonTablePageData getPersonTableData(SearchFilter filter) {
        PersonTablePageData pageData = new PersonTablePageData();

        String sql = SQLs.PERSON_PAGE_SELECT + SQLs.PERSON_PAGE_DATA_SELECT_INTO;
        SQL.selectInto(sql, new NVPair("page", pageData));

        return pageData;
    }
}
