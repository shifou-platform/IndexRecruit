package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.pojo.PositionCompanyBO;

import java.util.ArrayList;

public interface PositionMapper {

    @Select("select * from position where statePub = 1")
    ArrayList<PositionEntity> listPosAll();

    @Select("select * from position where positionId = #{posId} and statePub = 1")
    PositionEntity getPosition(@Param("posId") int posId);

    @Select("select * from position where hrIdPub = #{hrId} and statePub = 1 order by releaseDate DESC")
    ArrayList<PositionEntity> listHRPos(@Param("hrId") int hrId);

    @Select("select p.*,c.* from position p,department d,company c \n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId \n" +
            "and title like #{keyword} and statePub = 1 \n" +
            "order by releaseDate DESC")
    ArrayList<PositionCompanyBO> listSearchPos(@Param("keyword") String keyword);

    @Select("select p.*,c.* from position p,department d,company c\n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId \n" +
            "and categoryId = #{categoryId} and statePub = 1 \n" +
            "order by releaseDate DESC")
    ArrayList<PositionCompanyBO> listCategoryPos(@Param("categoryId") int categoryId);

    @Select("select count(*) from position where categoryId = #{categoryId}")
    int countCategoryPos(@Param("categoryId") int categoryId);

    @Update("update position set hits = hits+1 where positionId = #{positionId}")
    int updateHits(@Param("positionId") int positionId);

    @Select("select p.*,c.* from position p,department d,company c \n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId and p.positionId = #{posId} limit 1")
    PositionCompanyBO listPosCompany(@Param("posId") int posId);
}
