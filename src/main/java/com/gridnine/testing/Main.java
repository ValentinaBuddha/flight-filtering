package com.gridnine.testing;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int depYear;
        int depMonth;
        int depDay;

        int depHoursMin;
        int depMinutesMin;

        int depHoursMax;
        int depMinutesMax;

        int arrYear;
        int arrMonth;
        int arrDay;

        int arrHoursMin;
        int arrMinutesMin;

        int arrHoursMax;
        int arrMinutesMax;

        int maxDurationOfTransfers;
        int maxTravelTime;

        final FilteringOptions filters = new FilteringOptions();
        final List<Flight> flights = FlightBuilder.createFlights();

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            switch (command) {
                case 0:
                    System.out.println(Filtering.filterFlights(flights, filters));
                    break;
                case 1:
                    while (true) {
                        System.out.println("Введи номер года 2024 или 2025.");
                        depYear = scanner.nextInt();
                        if (depYear == 2024 || depYear == 2025) {
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    while (true) {
                        System.out.println("Введи номер месяца от 1 до 12.");
                        depMonth = scanner.nextInt();
                        if (depMonth >= 1 && depMonth <= 12) {
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    while (true) {
                        int lastDayOfMonth = LocalDate.of(depYear, depMonth, 1)
                                .getMonth().length(Year.of(depYear).isLeap());
                        System.out.println("Введи день от 1 до " + lastDayOfMonth + ".");
                        depDay = scanner.nextInt();
                        if (depDay >= 1 && depDay <= lastDayOfMonth) {
                            filters.setDepartureDate(LocalDate.of(depYear, depMonth, depDay));
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("""

                                Введи количество пересадок:
                                0 - 0 пересадок,
                                1 - 1 пересадка,
                                2 - 2 пересадки,
                                3 - количество пересадок не важно""");
                        int numberOfTransfers = scanner.nextInt();
                        if (numberOfTransfers >= 0 && numberOfTransfers <= 6) {
                            switch (numberOfTransfers) {
                                case 0:
                                    filters.setOneTransfer(false);
                                    filters.setTwoTransfers(false);
                                    break;
                                case 1:
                                    filters.setNoTransfer(false);
                                    filters.setTwoTransfers(false);
                                    break;
                                case 2:
                                    filters.setNoTransfer(false);
                                    filters.setOneTransfer(false);
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Такого количества пересадок нет.");
                                    break;
                            }
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println("Введи часы от 0 до 23.");
                        depHoursMin = scanner.nextInt();
                        if (depHoursMin >= 1 && depHoursMin <= 23) {
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    while (true) {
                        System.out.println("Введи минуты от 0 до 59.");
                        depMinutesMin = scanner.nextInt();
                        if (depMinutesMin >= 0 && depMinutesMin <= 59) {
                            filters.setDepartureTimeMin(LocalTime.of(depHoursMin, depMinutesMin));
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        System.out.println("Введи часы от 0 о 23.");
                        depHoursMax = scanner.nextInt();
                        if (depHoursMax >= 1 && depHoursMax <= 23) {
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    while (true) {
                        System.out.println("Введи минуты от 0 до 59.");
                        depMinutesMax = scanner.nextInt();
                        if (depMinutesMax >= 0 && depMinutesMax <= 59) {
                            filters.setDepartureTimeMax(LocalTime.of(depHoursMax, depMinutesMax));
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        System.out.println("Введи часы от 0 до 23.");
                        arrHoursMin = scanner.nextInt();
                        if (arrHoursMin >= 1 && arrHoursMin <= 23) {
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    while (true) {
                        System.out.println("Введи минуты от 0 до 59.");
                        arrMinutesMin = scanner.nextInt();
                        if (arrMinutesMin >= 0 && arrMinutesMin <= 59) {
                            filters.setArrivalTimeMin(LocalTime.of(arrHoursMin, arrMinutesMin));
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    break;
                case 6:
                    while (true) {
                        System.out.println("Введи часы от 0 о 23.");
                        arrHoursMax = scanner.nextInt();
                        if (arrHoursMax >= 1 && arrHoursMax <= 23) {
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    while (true) {
                        System.out.println("Введи минуты от 0 до 59.");
                        arrMinutesMax = scanner.nextInt();
                        if (arrMinutesMax >= 0 && arrMinutesMax <= 59) {
                            filters.setArrivalTimeMax(LocalTime.of(arrHoursMax, arrMinutesMax));
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    break;
                case 7:
                    while (true) {
                        System.out.println("Введи номер года 2024 или 2025.");
                        arrYear = scanner.nextInt();
                        if (arrYear == 2024 || arrYear == 2025) {
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    while (true) {
                        System.out.println("Введи номер месяца от 1 до 12.");
                        arrMonth = scanner.nextInt();
                        if (arrMonth >= 1 && arrMonth <= 12) {
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    while (true) {
                        int lastDayOfMonth = LocalDate.of(arrYear, arrMonth, 1)
                                .getMonth().length(Year.of(arrYear).isLeap());
                        System.out.println("Введи день от 1 до " + lastDayOfMonth + ".");
                        arrDay = scanner.nextInt();
                        if (arrDay >= 1 && arrDay <= lastDayOfMonth) {
                            filters.setArrivalDate(LocalDate.of(arrYear, arrMonth, arrDay));
                            break;
                        } else {
                            System.out.println("Ошибка! Введено неверное значение. Повторите ввод.");
                        }
                    }
                    break;
                case 8:
                    while (true) {
                        System.out.println("Введи максимальное время в пути в часах.");
                        maxTravelTime = scanner.nextInt();
                        if (maxTravelTime >= 0) {
                            filters.setMaxTravelTime(maxTravelTime);
                            break;
                        } else {
                            System.out.println("Ошибка! Введено отрицательное значение. Повтори ввод.");
                        }
                    }
                    break;
                case 9:
                    while (true) {
                        System.out.println("Введи максимальную длительность пересадок в часах.");
                        maxDurationOfTransfers = scanner.nextInt();
                        if (maxDurationOfTransfers >= 0) {
                            filters.setMaxDurationOfTransfers(maxDurationOfTransfers);
                            break;
                        } else {
                            System.out.println("Ошибка! Введено отрицательное значение. Повтори ввод.");
                        }
                    }
                    break;
                case 10:
                    filters.setFlightsFromNow(true);
                    break;
                case 11:
                    filters.setSegmentDatesFilter(true);
                    break;
                case 12:
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Извини, такой команды пока нет.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n0 - Получить список полётов");
        System.out.println("1 - Ввести самую раннюю дату вылета");
        System.out.println("2 - Выбрать количество пересадок");
        System.out.println("3 - Выбрать самое раннее время вылета");
        System.out.println("4 - Выбрать самое позднее время вылета");
        System.out.println("5 - Выбрать самое раннее время прилёта");
        System.out.println("6 - Выбрать самое позднее время прилёта");
        System.out.println("7 - Ввести дату прилёта");
        System.out.println("8 - Выбрать максимальное время в пути");
        System.out.println("9 - Выбрать максимальную длительность пересадок");
        System.out.println("10 - Исключить вылеты до текущего времени");
        System.out.println("11 - Исключить сегменты с датой прилёта раньше даты вылета");
        System.out.println("12 - Выйти из приложения");
    }
}