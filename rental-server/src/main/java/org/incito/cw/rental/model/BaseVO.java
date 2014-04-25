package org.incito.cw.rental.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 基类，记录实体id，创建时间（不可修改）和最后修改时间
 * 
 * @author Carl 2014年4月14日
 */
@MappedSuperclass
public class BaseVO implements Serializable {

  private static final long serialVersionUID = 1943229312100160406L;

  private String id;

  private Date createDate;

  private Date lastModified;

  @Column(name = "createDate", length = 32, nullable = true, updatable = false)
  public Date getCreateDate() {
    return createDate;
  }

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id", length = 32)
  public String getId() {
    return id;
  }

  @Column(name = "lastModified", length = 32, nullable = true)
  public Date getLastModified() {
    return lastModified;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

}
