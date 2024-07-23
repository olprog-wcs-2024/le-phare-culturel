package fr.olprog_c.le_phare_culturel.api.models;

import com.fasterxml.jackson.annotation.*;

public class Size {
  private long width;
  private long height;

  @JsonProperty("width")
  public long getWidth() {
    return width;
  }

  @JsonProperty("width")
  public void setWidth(long value) {
    this.width = value;
  }

  @JsonProperty("height")
  public long getHeight() {
    return height;
  }

  @JsonProperty("height")
  public void setHeight(long value) {
    this.height = value;
  }
}
