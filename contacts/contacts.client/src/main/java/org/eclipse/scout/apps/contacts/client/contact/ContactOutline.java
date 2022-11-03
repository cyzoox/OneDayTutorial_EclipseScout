package org.eclipse.scout.apps.contacts.client.contact;

import java.util.List;

import org.eclipse.scout.apps.contacts.client.organization.OrganizationTablePage;
import org.eclipse.scout.apps.contacts.client.person.PersonTablePage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;


import org.eclipse.scout.apps.contacts.shared.Icons;

/**
 * @author clintjamesrpascual
 */
@Order(1000)
public class ContactOutline extends AbstractOutline {

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    pageList.add(new PersonTablePage());
    pageList.add(new OrganizationTablePage());
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Contacts");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Folder;
  }
}
