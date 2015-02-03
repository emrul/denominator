package denominator.designate;

import java.util.List;
import java.util.Map;

import denominator.model.Zone;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

// http://docs.hpcloud.com/api/dns/#4.RESTAPISpecifications
public interface Designate {

  @RequestLine("GET /limits")
  Map<String, Object> limits();

  @RequestLine("GET /domains")
  List<Zone> domains();

  @RequestLine("GET /domains/{domainId}/records")
  List<Record> records(@Param("domainId") String domainId);

  @RequestLine("POST /domains/{domainId}/records")
  @Headers("Content-Type: application/json")
  Record createRecord(@Param("domainId") String domainId, Record record);

  @RequestLine("PUT /domains/{domainId}/records/{recordId}")
  @Headers("Content-Type: application/json")
  Record updateRecord(@Param("domainId") String domainId, @Param("recordId") String recordId,
                      Record record);

  @RequestLine("DELETE /domains/{domainId}/records/{recordId}")
  void deleteRecord(@Param("domainId") String domainId, @Param("recordId") String recordId);

  static class Record {

    String id;
    String name;
    String type;
    Integer ttl;
    String data;
    Integer priority;

    // toString ordering
    @Override
    public String toString() {
      return new StringBuilder(name).append(type).append(ttl).append(data).append(priority)
          .toString();
    }
  }
}
