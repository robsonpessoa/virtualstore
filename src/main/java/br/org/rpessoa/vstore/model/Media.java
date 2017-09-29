package br.org.rpessoa.vstore.model;

import br.org.rpessoa.vstore.dao.PostgresUuidConverter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "vs_media", schema = "vstore", catalog = "vstore_db")
@org.eclipse.persistence.annotations.Converter(
        name = "uuidConverter",
        converterClass = PostgresUuidConverter.class
)
public class Media {
    @Id
    @Column(name = "id", nullable = false)
    @org.eclipse.persistence.annotations.Convert("uuidConverter")
    private UUID uuid;

    @Column(name = "data", nullable = false)
    private byte[] data;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Media media = (Media) o;

        if (uuid != null ? !uuid.equals(media.uuid) : media.uuid != null) return false;
        return Arrays.equals(data, media.data);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @PrePersist
    private void generateUuid() {
        if (this.uuid == null)
            this.uuid = UUID.randomUUID();
    }
}
