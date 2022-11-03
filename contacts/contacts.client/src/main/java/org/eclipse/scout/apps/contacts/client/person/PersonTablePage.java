package org.eclipse.scout.apps.contacts.client.person;

import org.eclipse.scout.apps.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.apps.contacts.client.person.PersonTablePage.Table;
import org.eclipse.scout.apps.contacts.shared.person.IPersonService;
import org.eclipse.scout.apps.contacts.shared.person.PersonTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import java.util.Set;

@Data(PersonTablePageData.class)
public class PersonTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IPersonService.class).getPersonTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [clintjamesrpascual] verify translation
        return TEXTS.get("Person");
    }

    public class Table extends AbstractTable {
      public CityColumn getCityColumn() {
        return getColumnSet().getColumnByClass(CityColumn.class);
      }

      public CountryColumn getCountryColumn() {
        return getColumnSet().getColumnByClass(CountryColumn.class);
      }

      public EmailColumn getEmailColumn() {
        return getColumnSet().getColumnByClass(EmailColumn.class);
      }

      public FirstNameColumn getFirstNameColumn() {
        return getColumnSet().getColumnByClass(FirstNameColumn.class);
      }

      public LastNameColumn getLastNameColumn() {
        return getColumnSet().getColumnByClass(LastNameColumn.class);
      }

      public MobileColumn getMobileColumn() {
        return getColumnSet().getColumnByClass(MobileColumn.class);
      }

      public OrganizationColumn getOrganizationColumn() {
        return getColumnSet().getColumnByClass(OrganizationColumn.class);
      }

      public PersonIdColumn getPersonIdColumn() {
        return getColumnSet().getColumnByClass(PersonIdColumn.class);
      }

      public PhoneColumn getPhoneColumn() {
        return getColumnSet().getColumnByClass(PhoneColumn.class);
      }

      @Override
      protected Class<? extends IMenu> getConfiguredDefaultMenu() {
        return EditMenu.class;
      }

      @Order(10)
      public class EditMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
          return TEXTS.get("Edit");
        }

        @Override
        protected void execAction() {
          PersonForm form = new PersonForm();
          form.setPersonId(getPersonIdColumn().getSelectedValue());
          form.addFormListener(new PersonFormListener());
          // start the form using its modify handler
          form.startModify();
        }
      }

      @Order(20)
      public class NewMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("New");
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.<IMenuType> hashSet(
                  TableMenuType.EmptySpace, TableMenuType.SingleSelection);
        }

        @Override
        protected void execAction() {
          PersonForm form = new PersonForm();
          form.addFormListener(new PersonFormListener());
          // start the form using its new handler
          form.startNew();
        }
      }

      private class PersonFormListener implements FormListener {

        @Override
        public void formChanged(FormEvent e) {
          // reload page to reflect new/changed data after saving any changes
          if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
            reloadPage();
          }
        }
      }
      @Order(1000)
      public class PersonIdColumn extends AbstractStringColumn {
        @Override
        protected boolean getConfiguredDisplayable() {
          return false;
        }

        @Override
        protected boolean getConfiguredPrimaryKey() {
          return true;
        }
      }

      @Order(2000)
      public class FirstNameColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("FirstName");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(3000)
      public class LastNameColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("LastName");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(4000)
      public class CityColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("CityColumn");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(5000)
      public class CountryColumn extends AbstractSmartColumn<String> {

        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Country");
        }

        @Override
        protected int getConfiguredWidth() {
          return 120;
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return CountryLookupCall.class;
        }
      }

      @Order(6000)
      public class PhoneColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Phone");
        }
        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(7000)
      public class MobileColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Mobile");
        }
        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(8000)
      public class EmailColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Email");
        }
        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(9000)
      public class OrganizationColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Organization");
        }
        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }
    }
}
