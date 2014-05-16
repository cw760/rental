package org.incito.cw.rental.base;

import java.util.ArrayList;
import java.util.List;

public class Result<T> {

  private Integer status;

  private String message;

  private List<T> items;

  public Result() {

  }

  public Result(Integer status, String message) {
    this.status = status;
    this.message = message;
    this.items = new ArrayList<>();
  }

  public Result(Integer status, String message, List<T> items) {
    this.status = status;
    this.message = message;
    this.items = items;
  }

  public List<T> getItems() {
    return items;
  }

  public String getMessage() {
    return message;
  }

  public Integer getStatus() {
    return status;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}
