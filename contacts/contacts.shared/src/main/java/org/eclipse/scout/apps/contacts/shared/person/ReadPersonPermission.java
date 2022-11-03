package org.eclipse.scout.apps.contacts.shared.person;

import org.eclipse.scout.rt.security.AbstractPermission;

public class ReadPersonPermission extends AbstractPermission {
    private static final long serialVersionUID = 1L;

    public ReadPersonPermission() {
        super("ReadPersonPermission");
    }
}
