package br.com.letscode.star.wars.service;

import br.com.letscode.star.wars.config.DbTestBase;
import br.com.letscode.star.wars.dto.response.ReportResponseDto;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReportServiceIntegrationTest extends DbTestBase {

    @Autowired
    ReportService sut;

    @DatabaseSetup(value = "classpath:dbunit/com/letscode/star.wars/services/report-without-value-test.xml")
    @Test
    public void shouldReturnResponseWithoutData() {
        ReportResponseDto responseDto = sut.reports();
        Assert.assertNotNull(responseDto);
        Assert.assertNotNull(responseDto.getAverageResources());
        Assert.assertNull(responseDto.getLostPointsDueTraitors());
        Assert.assertEquals(responseDto.getRebelsPercentage(), BigDecimal.ZERO);
        Assert.assertEquals(responseDto.getTraitorsPercentage(), BigDecimal.ZERO);
    }

    @DatabaseSetup(value = "classpath:dbunit/com/letscode/star.wars/services/report-with-values-test.xml")
    @Test
    public void shouldReturnResponseWitData() {
        ReportResponseDto responseDto = sut.reports();
        Assert.assertNotNull(responseDto);
        Assert.assertNotNull(responseDto.getAverageResources());
        Assert.assertEquals(3, responseDto.getAverageResources().size());
        Assert.assertEquals(BigDecimal.valueOf(75.0000), responseDto.getRebelsPercentage().setScale(1));
        Assert.assertEquals(BigDecimal.valueOf(25.0000), responseDto.getTraitorsPercentage().setScale(1));
        Assert.assertEquals(Integer.valueOf(6), responseDto.getLostPointsDueTraitors());
    }
}
