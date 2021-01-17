package com.denning.demo.model;

import java.util.Objects;

/**
 * Item
 */
public class Item   {
  private String name;

  private String linkOrNotes;

  private Boolean bought = false;

  public Item name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Item linkOrNotes(String linkOrNotes) {
    this.linkOrNotes = linkOrNotes;
    return this;
  }

  /**
   * Get linkOrNotes
   * @return linkOrNotes
  */
  public String getLinkOrNotes() {
    return linkOrNotes;
  }

  public void setLinkOrNotes(String linkOrNotes) {
    this.linkOrNotes = linkOrNotes;
  }

  public Item bought(Boolean bought) {
    this.bought = bought;
    return this;
  }

  /**
   * Get bought
   * @return bought
  */
  public Boolean getBought() {
    return bought;
  }

  public void setBought(Boolean bought) {
    this.bought = bought;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(this.name, item.name) &&
        Objects.equals(this.linkOrNotes, item.linkOrNotes) &&
        Objects.equals(this.bought, item.bought);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, linkOrNotes, bought);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    linkOrNotes: ").append(toIndentedString(linkOrNotes)).append("\n");
    sb.append("    bought: ").append(toIndentedString(bought)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

