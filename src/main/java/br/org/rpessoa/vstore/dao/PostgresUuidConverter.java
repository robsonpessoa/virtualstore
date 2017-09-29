package br.org.rpessoa.vstore.dao;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.DirectCollectionMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

import java.util.UUID;

public class PostgresUuidConverter implements Converter {
    @Override
    public Object convertObjectValueToDataValue(Object objectValue,
                                                Session session) {
        return objectValue;
    }


    @Override
    public UUID convertDataValueToObjectValue(Object dataValue,
                                              Session session) {
        return (UUID) dataValue;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        final DatabaseField field;
        if (mapping instanceof DirectCollectionMapping) {
            field = ((DirectCollectionMapping) mapping).getDirectField();
        } else {
            field = mapping.getField();
        }

        field.setSqlType(java.sql.Types.OTHER);
        field.setTypeName("java.util.UUID");
        field.setColumnDefinition("UUID");
    }
}