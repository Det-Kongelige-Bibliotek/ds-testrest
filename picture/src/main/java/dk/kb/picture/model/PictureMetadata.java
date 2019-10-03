package dk.kb.picture.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * PictureMetadata
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-03T13:53:56.637+02:00[Europe/Oslo]")

public class PictureMetadata   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("pictureName")
  private String pictureName;

  public PictureMetadata id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PictureMetadata pictureName(String pictureName) {
    this.pictureName = pictureName;
    return this;
  }

  /**
   * Picture Name
   * @return pictureName
  */
  @ApiModelProperty(value = "Picture Name")


  public String getPictureName() {
    return pictureName;
  }

  public void setPictureName(String pictureName) {
    this.pictureName = pictureName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PictureMetadata pictureMetadata = (PictureMetadata) o;
    return Objects.equals(this.id, pictureMetadata.id) &&
        Objects.equals(this.pictureName, pictureMetadata.pictureName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pictureName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PictureMetadata {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    pictureName: ").append(toIndentedString(pictureName)).append("\n");
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

