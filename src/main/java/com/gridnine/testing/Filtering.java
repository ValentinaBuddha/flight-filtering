package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Utility class to filtering flights.
 */
public class Filtering {

    public static List<Flight> filterFlights(List<Flight> flights, FilteringOptions filters) {
        List<Flight> filteredFlights = new ArrayList<>(flights);

        if (filters.getDepartureDate() != null) {
            filteredFlights = filteredFlights.stream()
                    .filter(flight -> flight.getSegments().get(0).getDepartureDate().toLocalDate()
                            .isAfter(filters.getDepartureDate().minusDays(1)))
                    .collect(Collectors.toList());
        }
        if (filters.getDepartureTimeMin() != null) {
            filteredFlights = filteredFlights.stream()
                    .filter(flight -> flight.getSegments().get(0).getDepartureDate().toLocalTime()
                            .isAfter(filters.getDepartureTimeMin().minusMinutes(1)))
                    .collect(Collectors.toList());
        }
        if (filters.getDepartureTimeMax() != null) {
            filteredFlights = filteredFlights.stream()
                    .filter(flight -> flight.getSegments().get(0).getDepartureDate().toLocalTime()
                            .isBefore(filters.getDepartureTimeMax().plusMinutes(1)))
                    .collect(Collectors.toList());
        }
        if (filters.getArrivalTimeMin() != null) {
            List<Flight> flightsForRemove = new ArrayList<>();
            for (Flight flight : filteredFlights) {
                List<Segment> segments = flight.getSegments();
                if (segments.get(segments.size() - 1).getArrivalDate().toLocalTime()
                        .isBefore(filters.getArrivalTimeMin())) {
                    flightsForRemove.add(flight);
                }
            }
            filteredFlights.removeAll(flightsForRemove);
        }
        if (filters.getArrivalTimeMax() != null) {
            List<Flight> flightsForRemove = new ArrayList<>();
            for (Flight flight : filteredFlights) {
                List<Segment> segments = flight.getSegments();
                if (segments.get(segments.size() - 1).getArrivalDate().toLocalTime()
                        .isAfter(filters.getArrivalTimeMax())) {
                    flightsForRemove.add(flight);
                }
            }
            filteredFlights.removeAll(flightsForRemove);
        }
        if (filters.getArrivalDate() != null) {
            List<Flight> flightsForRemove = new ArrayList<>();
            for (Flight flight : filteredFlights) {
                List<Segment> segments = flight.getSegments();
                if (!segments.get(segments.size() - 1).getArrivalDate().toLocalDate().equals(filters.getArrivalDate())) {
                    flightsForRemove.add(flight);
                }
            }
            filteredFlights.removeAll(flightsForRemove);
        }
        if (filters.isNoTransfer() && !filters.isOneTransfer() && !filters.isTwoTransfers()) {
            filteredFlights = filteredFlights.stream()
                    .filter(flight -> flight.getSegments().size() == 1)
                    .collect(Collectors.toList());
        }
        if (filters.isOneTransfer() && !filters.isNoTransfer() && !filters.isTwoTransfers()) {
            filteredFlights = filteredFlights.stream()
                    .filter(flight -> flight.getSegments().size() == 2)
                    .collect(Collectors.toList());
        }
        if (filters.isTwoTransfers() && !filters.isNoTransfer() && !filters.isOneTransfer()) {
            filteredFlights = filteredFlights.stream()
                    .filter(flight -> flight.getSegments().size() == 3)
                    .collect(Collectors.toList());
        }
        if (filters.getMaxTravelTime() != null) {
            List<Flight> flightsForRemove = new ArrayList<>();
            for (Flight flight : filteredFlights) {
                List<Segment> segments = flight.getSegments();
                Duration duration = Duration.between(segments.get(0).getDepartureDate(),
                        segments.get(segments.size() - 1).getArrivalDate());
                long hours = TimeUnit.HOURS.toHours(TimeUnit.MILLISECONDS.toHours(duration.toMillis()));
                if (hours > (long) filters.getMaxTravelTime() || hours < 0) {
                    flightsForRemove.add(flight);
                }
            }
            filteredFlights.removeAll(flightsForRemove);
        }
        if (filters.getMaxDurationOfTransfers() != null) {
            List<Flight> flightsForRemove = new ArrayList<>();
            for (Flight flight : filteredFlights) {
                List<Segment> segments = flight.getSegments();
                long hours = 0;
                if (segments.size() > 1) {
                    for (int i = 0; i < (segments.size() - 1); i++) {
                        Duration duration = Duration.between(segments.get(i).getArrivalDate(),
                                segments.get(i + 1).getDepartureDate());
                        hours += TimeUnit.HOURS.toHours(TimeUnit.MILLISECONDS.toHours(duration.toMillis()));
                    }
                }
                if (hours > (long) filters.getMaxDurationOfTransfers()) {
                    flightsForRemove.add(flight);
                }
            }
            filteredFlights.removeAll(flightsForRemove);
        }
        if (filters.isFlightsFromNow()) {
            filteredFlights = filteredFlights.stream()
                    .filter(flight -> flight.getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now()))
                    .collect(Collectors.toList());
        }
        if (filters.isSegmentDatesFilter()) {
            List<Flight> flightsForRemove = new ArrayList<>();
            for (Flight flight : filteredFlights) {
                for (Segment segment : flight.getSegments()) {
                    if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                        flightsForRemove.add(flight);
                    }
                }
            }
            filteredFlights.removeAll(flightsForRemove);
        }
        return filteredFlights;
    }
}