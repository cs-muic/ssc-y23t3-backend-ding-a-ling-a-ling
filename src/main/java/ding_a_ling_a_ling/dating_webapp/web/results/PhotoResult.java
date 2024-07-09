package ding_a_ling_a_ling.dating_webapp.web.results;

import ding_a_ling_a_ling.dating_webapp.domain.common.file.FileUrlCreator;
import ding_a_ling_a_ling.dating_webapp.utils.ImageUtils;
import org.springframework.http.ResponseEntity;

public class PhotoResult {

  public static ResponseEntity<ApiResult> build(ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo photo, FileUrlCreator fileUrlCreator) {
    String fileUrl = fileUrlCreator.url(photo.getFilePath());
    ApiResult apiResult = ApiResult.blank()
      .add("id", photo.getId().value())
      .add("fileName", photo.getFileName())
      .add("fileType", photo.getFileType())
      .add("fileUrl", fileUrl)
      .add("userId", photo.getUserId().value());

    return Result.ok(apiResult);
  }
}
