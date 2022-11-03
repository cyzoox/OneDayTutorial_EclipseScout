package org.eclipse.scout.apps.contacts.client.person;

import org.eclipse.scout.apps.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.CancelButton;
import org.eclipse.scout.apps.contacts.client.person.PersonForm.MainBox.OkButton;
import org.eclipse.scout.apps.contacts.shared.Icons;
import org.eclipse.scout.apps.contacts.shared.person.*;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {
    private String personId;

    @FormData
    public String getPersonId() {
        return personId;
    }

    @FormData
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public Object computeExclusiveKey() {
        return getPersonId();
    }

    @Override
    protected int getConfiguredDisplayHint() {
        return IForm.DISPLAY_HINT_VIEW;
    }

    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("Person");
    }


    public MainBox getMainBox() {
        return getFieldByClass(MainBox.class);
    }



    public OkButton getOkButton() {
        return getFieldByClass(OkButton.class);
    }

    public CancelButton getCancelButton() {
        return getFieldByClass(CancelButton.class);
    }

    public MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CityField getCityField() {
        return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CityField.class);
    }

    public MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CountryField getCountryField() {
        return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CountryField.class);
    }

    public MainBox.GeneralBox.DateOfBirthField getDateOfBirthField() {
        return getFieldByClass(MainBox.GeneralBox.DateOfBirthField.class);
    }

    public MainBox.DetailsBox.ContactInfoBox.EmailField getEmailField() {
        return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.EmailField.class);
    }

    public MainBox.DetailsBox.WorkBox.EmailWorkField getEmailWorkField() {
        return getFieldByClass(MainBox.DetailsBox.WorkBox.EmailWorkField.class);
    }

    public MainBox.GeneralBox.FirstNameField getFirstNameField() {
        return getFieldByClass(MainBox.GeneralBox.FirstNameField.class);
    }

    public MainBox.GeneralBox.LastNameField getLastNameField() {
        return getFieldByClass(MainBox.GeneralBox.LastNameField.class);
    }

    public MainBox.DetailsBox.ContactInfoBox.MobileField getMobileField() {
        return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.MobileField.class);
    }

    public MainBox.DetailsBox.NotesBox.NotesField getNotesField() {
        return getFieldByClass(MainBox.DetailsBox.NotesBox.NotesField.class);
    }

    public MainBox.DetailsBox.WorkBox.OrganizationField getOrganizationField() {
        return getFieldByClass(MainBox.DetailsBox.WorkBox.OrganizationField.class);
    }

    public MainBox.DetailsBox.ContactInfoBox.PhoneField getPhoneField() {
        return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.PhoneField.class);
    }

    public MainBox.DetailsBox.WorkBox.PhoneWorkField getPhoneWorkField() {
        return getFieldByClass(MainBox.DetailsBox.WorkBox.PhoneWorkField.class);
    }

    public MainBox.DetailsBox.WorkBox.PositionField getPositionField() {
        return getFieldByClass(MainBox.DetailsBox.WorkBox.PositionField.class);
    }

    public MainBox.DetailsBox.ContactInfoBox.AddressBox.StreetField getStreetField() {
        return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.AddressBox.StreetField.class);
    }

    @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class GeneralBox extends AbstractGroupBox {
            public class PictureUrlField extends AbstractStringField {

                @Override
                protected boolean getConfiguredVisible() {
                    return false;
                }
            }

            @Order(20)
            public class PictureField extends AbstractImageField {

                @Override
                protected Class<PictureUrlField> getConfiguredMasterField() {
                    return PictureUrlField.class;
                }

                @Override
                protected void execChangedMasterValue(Object newMasterValue) {
                    updateImage((String) newMasterValue);
                }

                @Override
                protected boolean getConfiguredLabelVisible() {
                    return false;
                }

                @Override
                protected int getConfiguredGridH() {
                    return 5;
                }

                @Override
                protected boolean getConfiguredAutoFit() {
                    return true;
                }

                @Override
                protected String getConfiguredImageId() {
                    return Icons.User;
                }

                protected void updateImage(String url) {
                    setImageUrl(url);
                }
            }

            @Order(2000)
            public class FirstNameField extends AbstractStringField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("FirstName");
                }

                @Override
                protected int getConfiguredMaxLength() {
                    return 128;
                }
            }

            @Order(3000)
            public class LastNameField extends AbstractStringField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("LastName");
                }

                @Override
                protected int getConfiguredMaxLength() {
                    return 128;
                }
            }

            @Order(4000)
            public class DateOfBirthField extends AbstractDateField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("DateOfBirth");
                }
            }

            @Order(60)
            public class GenderGroup extends AbstractRadioButtonGroup<String> {

                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Gender");
                }

                @Override
                protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
                    return GenderCodeType.class;
                }
            }
        }

        @Order(2000)
        public class DetailsBox extends AbstractTabBox {

            @Order(10)
            public class ContactInfoBox extends AbstractGroupBox {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("ContactInfo");
                }

                @Order(1000)
                public class PhoneField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Phone");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 128;
                    }
                }

                @Order(2000)
                public class MobileField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Mobile");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 128;
                    }
                }

                @Order(3000)
                public class EmailField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Email");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 128;
                    }
                }

                public class AddressBox extends AbstractGroupBox {

                    @Override
                    protected boolean getConfiguredBorderVisible() {
                        return false;
                    }

                    @Override
                    protected int getConfiguredGridH() {
                        return 3;
                    }

                    @Override
                    protected int getConfiguredGridW() {
                        return 1;
                    }

                    @Override
                    protected int getConfiguredGridColumnCount() {
                        return 1;
                    }



                    @Order(10)
                    public class StreetField extends AbstractStringField {

                        @Override
                        protected String getConfiguredLabel() {
                            return TEXTS.get("Street");
                        }
                    }

                    // use a sequence box for horizontal layout
                    @Order(20)
                    public class LocationBox extends AbstractSequenceBox {

                        @Override
                        protected String getConfiguredLabel() {
                            return TEXTS.get("Location");
                        }

                        @Override
                        protected boolean getConfiguredAutoCheckFromTo() {
                            return false;
                        }


                        @Order(10)
                        public class CityField extends AbstractStringField {

                            @Override
                            protected String getConfiguredLabel() {
                                return TEXTS.get("City");
                            }

                            @Override
                            protected byte getConfiguredLabelPosition() {
                                return LABEL_POSITION_ON_FIELD;
                            }
                        }



                        @Order(20)
                        public class CountryField extends AbstractSmartField<String> {

                            @Override
                            protected String getConfiguredLabel() {
                                return TEXTS.get("Country");
                            }

                            @Override
                            protected byte getConfiguredLabelPosition() {
                                return LABEL_POSITION_ON_FIELD;
                            }

                            @Override
                            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
                                return CountryLookupCall.class;
                            }
                        }
                    }
                }
            }

            @Order(20)
            public class WorkBox extends AbstractGroupBox {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Work");
                }
                @Order(1000)
                public class PositionField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Phone");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 128;
                    }
                }

                @Order(2000)
                public class OrganizationField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Organization");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 128;
                    }
                }

                @Order(3000)
                public class PhoneWorkField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Phone");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 128;
                    }
                }

                @Order(4000)
                public class EmailWorkField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Email");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 128;
                    }
                }

            }

            @Order(30)
            public class NotesBox extends AbstractGroupBox {

                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Notes");
                }

                @Order(10)
                public class NotesField extends AbstractStringField {

                    @Override
                    protected int getConfiguredGridH() {
                        return 4;
                    }

                    @Override
                    protected boolean getConfiguredLabelVisible() {
                        return false;
                    }

                    @Override
                    protected boolean getConfiguredMultilineText() {
                        return true;
                    }
                }
            }
        }


        @Order(3000)
        public class OkButton extends AbstractOkButton {

        }

        @Order(4000)
        public class CancelButton extends AbstractCancelButton {

        }
    }

    public void startModify() {
        startInternalExclusive(new ModifyHandler());
    }

    public void startNew() {
        startInternal(new NewHandler());
    }

    public class ModifyHandler extends AbstractFormHandler {

        @Override
        protected void execLoad() {
            IPersonService service = BEANS.get(IPersonService.class);
            PersonFormData formData = new PersonFormData();
            exportFormData(formData);
            formData = service.load(formData);
            importFormData(formData);

            getForm().setSubTitle(calculateSubTitle());
        }

        @Override
        protected void execStore() {
            IPersonService service = BEANS.get(IPersonService.class);
            PersonFormData formData = new PersonFormData();
            exportFormData(formData);
            service.store(formData);
        }
    }

    public class NewHandler extends AbstractFormHandler {

        @Override
        protected void execStore() {
            IPersonService service = BEANS.get(IPersonService.class);
            PersonFormData formData = new PersonFormData();
            exportFormData(formData);
            formData = service.create(formData);
            importFormData(formData);
        }
    }

    protected String calculateSubTitle() {
        return StringUtility.join(" ",
                getFirstNameField().getValue(),
                getLastNameField().getValue());
    }
}
