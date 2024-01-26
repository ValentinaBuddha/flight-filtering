package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FilteringTest {
    private LocalDateTime date;
    private LocalDateTime pastDate;
    private Segment segment1;
    private Segment segment2;
    private Segment segment3;
    private Segment segment4;
    private Segment segment5;
    private Segment segment6;
    private Segment segment7;
    private Segment segment8;
    private Segment segment9;
    private Segment segment10;
    private Flight flight1;
    private Flight flight2;
    private Flight flight3;
    private Flight flight4;
    private Flight flight5;
    private Flight flight6;
    final private List<Flight> flights = new ArrayList<>();
    final private FilteringOptions filters = new FilteringOptions();


    @BeforeEach
    void setUp() {
        date = LocalDateTime.of(2024, 2, 19, 15, 0, 0);
        pastDate = LocalDateTime.of(2024, 1, 18, 15, 0, 0);
        segment1 = new Segment(date.minusHours(2), date.plusHours(2));
        segment2 = new Segment(date.minusDays(1), date.plusHours(2));
        segment3 = new Segment(date.plusHours(3), date.plusHours(5));
        segment4 = new Segment(pastDate, date.minusDays(1));
        segment5 = new Segment(date.plusHours(4), date.minusHours(6));
        segment6 = new Segment(date, date.plusHours(2));
        segment7 = new Segment(date.plusHours(5), date.plusHours(6));
        segment8 = new Segment(date, date.plusHours(2));
        segment9 = new Segment(date.plusHours(3), date.plusHours(4));
        segment10 = new Segment(date.plusHours(6), date.plusHours(7));
        flight1 = new Flight(List.of(segment1));
        flight2 = new Flight(List.of(segment2, segment3));
        flight3 = new Flight(List.of(segment4));
        flight4 = new Flight(List.of(segment5));
        flight5 = new Flight(List.of(segment6, segment7));
        flight6 = new Flight(List.of(segment8, segment9, segment10));
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
        flights.add(flight6);
    }

    @Test
    void filterFlights_whenNoFilters_thenReturnAllFlights() {
        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(6, filteredFlights.size());
        assertEquals(date.minusHours(2), filteredFlights.get(0).getSegments().get(0).getDepartureDate());
        assertEquals(date.plusHours(2), filteredFlights.get(0).getSegments().get(0).getArrivalDate());
        assertEquals(1, filteredFlights.get(0).getSegments().size());
    }

    @Test
    void filterFlights_whenDateTimeNow_thenFindAllFlightsFromThisMoment() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment12 = new Segment(now.minusHours(2), now);
        Segment segment13 = new Segment(now.plusMinutes(1), now.plusHours(2));
        Flight flight7 = new Flight(List.of(segment12));
        Flight flight8 = new Flight(List.of(segment13));
        flights.add(flight7);
        flights.add(flight8);
        filters.setFlightsFromNow(true);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(6, filteredFlights.size());
        assertEquals(now.plusMinutes(1), filteredFlights.get(5).getSegments().get(0).getDepartureDate());
        assertEquals(segment13, filteredFlights.get(5).getSegments().get(0));
        assertEquals(1, filteredFlights.get(5).getSegments().size());
    }

    @Test
    void filterFlights_whenDepartureDate_thenFindAllFlightsWithThisDepartureDate() {
        filters.setDepartureDate(LocalDate.of(2024, 2, 19));

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(4, filteredFlights.size());
        assertEquals(date.plusHours(4), filteredFlights.get(1).getSegments().get(0).getDepartureDate());
        assertEquals(1, filteredFlights.get(1).getSegments().size());
    }

    @Test
    void filterFlights_whenMinimumDepartureTime_thenFindAllFlightsWithDepartureFromThisTime() {
        filters.setDepartureTimeMin(LocalTime.of(18, 0));

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(1, filteredFlights.size());
        assertEquals(flight4, filteredFlights.get(0));
        assertEquals(1, filteredFlights.get(0).getSegments().size());
    }

    @Test
    void filterFlights_whenMaximumDepartureTime_thenFindAllFlightsWithDepartureUntilThisTime() {
        filters.setDepartureTimeMax(LocalTime.of(13, 0));

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(1, filteredFlights.size());
        assertEquals(segment1, filteredFlights.get(0).getSegments().get(0));
    }

    @Test
    void filterFlights_whenMinimumArrivalTime_thenFindAllFlightsWithArrivalFromThisTime() {
        LocalTime arrTimeMin = LocalTime.of(22, 0);
        filters.setArrivalTimeMin(arrTimeMin);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(1, filteredFlights.size());
        assertEquals(flight6, filteredFlights.get(0));
        assertEquals(3, filteredFlights.get(0).getSegments().size());
        assertEquals(arrTimeMin, filteredFlights.get(0).getSegments().get(2).getArrivalDate().toLocalTime());
    }

    @Test
    void filterFlights_whenMaximumArrivalTime_thenFindAllFlightsWithArrivalUntilThisTime() {
        filters.setArrivalTimeMax(LocalTime.of(9, 0));

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(1, filteredFlights.size());
        assertEquals(flight4, filteredFlights.get(0));
    }

    @Test
    void filterFlights_whenArrivalDate_thenFindAllFlightsWithArrivalDate() {
        LocalDate arrDate = LocalDate.of(2024, 2, 18);
        filters.setArrivalDate(arrDate);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(1, filteredFlights.size());
        assertEquals(arrDate, filteredFlights.get(0).getSegments().get(0).getArrivalDate().toLocalDate());
        assertEquals(flight3, filteredFlights.get(0));
        assertEquals(1, filteredFlights.get(0).getSegments().size());
    }

    @Test
    void filterFlights_whenMaximumTravelTime_thenFindAllFlightsWithTravelTimeLessThanMaximum() {
        filters.setMaxTravelTime(6);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(2, filteredFlights.size());
        assertEquals(flight1, filteredFlights.get(0));
        assertEquals(flight5, filteredFlights.get(1));
    }

    @Test
    void filterFlights_whenMaximumTransferTime_thenFindAllFlightsWithTransferTimeLessThanMaximum() {
        filters.setMaxDurationOfTransfers(2);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(4, filteredFlights.size());
        assertEquals(flight4, filteredFlights.get(3));
    }

    @Test
    void filterFlights_whenDateOfSegmentsWrong_thenFindAllFlightsWithoutFlightsWithThisSegments() {
        filters.setSegmentDatesFilter(true);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(5, filteredFlights.size());
        assertEquals(flight5, filteredFlights.get(3));
    }

    @Test
    void filterFlights_whenNoTransfer_thenFindAllFlightsWithoutTransfer() {
        filters.setOneTransfer(false);
        filters.setTwoTransfers(false);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(3, filteredFlights.size());
        assertEquals(flight4, filteredFlights.get(2));
    }

    @Test
    void filterFlights_whenOneTransfer_thenFindAllFlightsWithOneTransfer() {
        filters.setNoTransfer(false);
        filters.setTwoTransfers(false);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(2, filteredFlights.size());
        assertEquals(flight2, filteredFlights.get(0));
    }

    @Test
    void filterFlights_whenTwoTransfer_thenFindAllFlightsWithTwoTransfers() {
        filters.setNoTransfer(false);
        filters.setOneTransfer(false);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(1, filteredFlights.size());
        assertEquals(flight6, filteredFlights.get(0));
    }

    @Test
    void filterFlights_whenFewFilters_thenFindAllFlightsWithThisFilters() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment12 = new Segment(now.plusMinutes(1), now.plusHours(2));
        Segment segment13 = new Segment(now.plusHours(3), now.plusHours(4));
        Segment segment14 = new Segment(date.minusHours(5), date.minusHours(3));
        Flight flight7 = new Flight(List.of(segment12, segment13));
        Flight flight8 = new Flight(List.of(segment14));
        flights.add(flight7);
        flights.add(flight8);
        filters.setFlightsFromNow(true);
        filters.setSegmentDatesFilter(true);
        filters.setMaxTravelTime(20);
        filters.setMaxDurationOfTransfers(2);
        filters.setDepartureTimeMax(LocalTime.of(11, 0));
        filters.setOneTransfer(false);
        filters.setTwoTransfers(false);

        List<Flight> filteredFlights = Filtering.filterFlights(flights, filters);

        assertNotNull(filteredFlights);
        assertEquals(1, filteredFlights.size());
        assertEquals(flight8, filteredFlights.get(0));
    }
}