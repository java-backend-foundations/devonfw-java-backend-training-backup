package com.devonfw.app.java.order.general.dataaccess.api;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Column;

import com.devonfw.app.java.order.general.common.api.BinaryObject;

/**
 * {@link ApplicationPersistenceEntity Entity} for {@link BinaryObject}. Contains the actual {@link Blob}.
 */
@Entity
@Table(name = "BinaryObject")
public class BinaryObjectEntity extends ApplicationPersistenceEntity implements BinaryObject {

  private static final long serialVersionUID = 1L;

  private Blob data;

  private String mimeType;

  private long size;

  /**
   * The constructor.
   */
  public BinaryObjectEntity() {

    super();
  }

  @Override
  public void setMimeType(String mimeType) {

    this.mimeType = mimeType;

  }

  @Override
  public String getMimeType() {

    return this.mimeType;
  }

  /**
   * @return the {@link Blob} data.
   */
  @Lob
  @Column(name = "content")
  public Blob getData() {

    return this.data;
  }

  /**
   * @param data the data to set
   */
  public void setData(Blob data) {

    this.data = data;
  }

  @Column(name = "filesize")
  @Override
  public long getSize() {

    return this.size;
  }

  @Override
  public void setSize(long size) {

    this.size = size;
  }
}
