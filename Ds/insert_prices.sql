select flights.departure_airport,
       flights.arrival_airport,
       ticket_flights.fare_conditions,
       flights.aircraft_code,
       bp.seat_no,
       avg(ticket_flights.amount),
       min(ticket_flights.amount),
       max(ticket_flights.amount)
from ticket_flights
join flights on flights.flight_id = ticket_flights.flight_id
join boarding_passes bp on bp.ticket_no = ticket_flights.ticket_no and bp.flight_id = flights.flight_id
group by flights.departure_airport,
         flights.arrival_airport,
         ticket_flights.fare_conditions,
         bp.seat_no,
         flights.aircraft_code;