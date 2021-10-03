package br.com.letscode.star.wars.service;

import br.com.letscode.star.wars.config.DbTestBase;
import br.com.letscode.star.wars.constant.ItemType;
import br.com.letscode.star.wars.domain.Rebel;
import br.com.letscode.star.wars.dto.request.ItemRequestDto;
import br.com.letscode.star.wars.dto.request.LocationRequestDto;
import br.com.letscode.star.wars.dto.request.RebelRequestDto;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RebelServiceIntegrationTest extends DbTestBase {

    @Autowired
    RebelService sut;

    @DatabaseSetup(value = "classpath:dbunit/com/letscode/star.wars/services/create-rebel-service-test.xml")
    @ExpectedDatabase(
            value = "classpath:dbunit/com/letscode/star.wars/services/create-rebel-service-test-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
    public void shouldCreateRebel() {
        RebelRequestDto requestDto = RebelRequestDto.builder().age(1).genre("bla").location(LocationRequestDto.builder()
                        .name("location")
                        .latitude(123445L)
                        .longitude(2312312L)
                        .build()).name("xptop")
                .inventory(Collections.singletonList(ItemRequestDto.builder().itemType(ItemType.AMMUNITION).quantity(1).build())).build();
        Rebel rebel = sut.save(requestDto);
        Assert.assertNotNull(rebel);
        Assert.assertNotNull(rebel.getId());
    }

    @DatabaseSetup(value = "classpath:dbunit/com/letscode/star.wars/services/update-rebel-location-service-test.xml")
    @ExpectedDatabase(
            value = "classpath:dbunit/com/letscode/star.wars/services/update-rebel-location-service-test-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
    public void shouldUpdateRebelLocation() {
        LocationRequestDto locationRequestDto = LocationRequestDto.builder().longitude(4444L).latitude(4444L).name("locationUpdated").build();

        Rebel rebel = sut.updateLocation(locationRequestDto, 1L);
        Assert.assertNotNull(rebel);
        Assert.assertNotNull(rebel.getId());
    }

    @DatabaseSetup(value = "classpath:dbunit/com/letscode/star.wars/services/update-rebel-traitor-mark-report-test.xml")
    @ExpectedDatabase(
            value = "classpath:dbunit/com/letscode/star.wars/services/update-rebel-traitor-report-service-test-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
    public void shouldInformTraitorWithoutMarkingRebelAsTraitor() {
        Rebel rebel = sut.informTraitor(1L);
        Assert.assertNotNull(rebel);
        Assert.assertNotNull(rebel.getId());
    }

    @DatabaseSetup(value = "classpath:dbunit/com/letscode/star.wars/services/set-rebel-as-traitor-service-test.xml")
    @ExpectedDatabase(
            value = "classpath:dbunit/com/letscode/star.wars/services/set-rebel-as-traitor-service-test-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
    public void shouldMarkRebelAsTraitor() {
        Rebel rebel = sut.informTraitor(1L);
        Assert.assertNotNull(rebel);
        Assert.assertNotNull(rebel.getId());
    }

    @Test(expected = ResponseStatusException.class)
    @DatabaseSetup(value = "classpath:dbunit/com/letscode/star.wars/services/create-rebel-service-test.xml")
    public void shouldThrowExceptionBecauseRequestIsNotValid() {
        sut.save(RebelRequestDto.builder().build());
    }

    @Test(expected = ResponseStatusException.class)
    @DatabaseSetup(value = "classpath:dbunit/com/letscode/star.wars/services/create-rebel-service-test.xml")
    public void shouldThrowExceptionBecauseRebelNotExists() {
        sut.updateLocation(LocationRequestDto.builder().build(), 2L);
    }

    @Test(expected = ResponseStatusException.class)
    @DatabaseSetup(value = "classpath:dbunit/com/letscode/star.wars/services/create-rebel-service-test.xml")
    public void shouldThrowExceptionBecauseRebelNotExists2() {
        sut.informTraitor(2L);
    }
}
