prompt Question 1 - drafiei
  select t.email
  from flights f, bookings b, tickets t
  where f.dst='YEG' and f.flightno=b.flightno and b.tno=t.tno;

prompt Question 2 - drafiei
  select distinct p.name, p.email
  from airports a, flights f, sch_flights s, bookings b, tickets t, passengers p 
  where a.city='Edmonton' and a.acode=f.src and f.flightno=s.flightno and s.flightno=b.flightno
        and s.dep_date=b.dep_date and b.tno=t.tno and extract(month from s.dep_date)=12
        and extract(year from s.dep_date)=2015 and  t.email=p.email;

prompt Question 3 - drafiei
  select flightno, dep_date
  from sch_flights
  where dep_date > (select sysdate from dual) and
        dep_date < (select sysdate+30 from dual)
  minus
  select flightno, dep_date
  from bookings;

prompt Question 4 - drafiei
  select f.dst, count(*)
  from flights f, sch_flights sf 
  where f.flightno=sf.flightno 
  group by f.dst
  having count(*) >= all (select count(*)
			  from flights f, sch_flights sf
  			  where f.flightno=sf.flightno 
  			  group by f.dst);

prompt Question 5 - drafiei
  select acode, cnt
  from (select acode, count(*) cnt, row_number() over (order by count(*) desc) rn
        from airports a, flights f, sch_flights sf
        where (a.acode=f.src or a.acode=f.dst) and f.flightno=sf.flightno
        group by a.acode) t
  where rn <=3;

prompt Question 6 - drafiei
  select fa.flightno, to_char(f.dep_time,'HH24:MI') as dep_time, fa.fare, fa.price 
  from airports s, airports d, flights f, flight_fares fa
  where s.acode=f.src and d.acode=f.dst and s.city='Edmonton' and d.city='Los Angeles' and
        f.flightno=fa.flightno and price <= all
    (select price 
     from airports s, airports d, flights f, flight_fares fa
     where s.acode=f.src and d.acode=f.dst and s.city='Edmonton' and d.city='Los Angeles' and
           f.flightno=fa.flightno);

prompt Question 7 - drafiei
  drop table available_flights;
  create view available_flights(flightno,dep_date, src,dst,dep_time,arr_time,fare,seats,
  	price) as 
  select f.flightno, sf.dep_date, f.src, f.dst, f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time)), 
	 f.dep_time+(trunc(sf.dep_date)-trunc(f.dep_time))+(f.est_dur/60+a2.tzone-a1.tzone)/24, 
         fa.fare, fa.limit-count(tno), fa.price
  from flights f, flight_fares fa, sch_flights sf, bookings b, airports a1, airports a2
  where f.flightno=sf.flightno and f.flightno=fa.flightno and f.src=a1.acode and
	f.dst=a2.acode and fa.flightno=b.flightno(+) and fa.fare=b.fare(+) and
	sf.dep_date=b.dep_date(+)
  group by f.flightno, sf.dep_date, f.src, f.dst, f.dep_time, f.est_dur,a2.tzone,
	a1.tzone, fa.fare, fa.limit, fa.price
  having fa.limit-count(tno) > 0;

  select flightno, dep_date, src, dst, to_char(dep_time,'HH24:MI'), 
         to_char(arr_time, 'HH24:MI'), fare, seats, price 
  from available_flights
  order by dep_date;

prompt Question 8 - drafiei
  select flightno, fare, seats, price
  from available_flights
  where to_char(dep_date,'DD/MM/YYYY')='22/12/2015' and src='YEG' and dst='YYZ' and
        price <= all (select price 
  		      from available_flights
		      where to_char(dep_date,'DD/MM/YYYY')='22/12/2015' and src='YEG' and dst='YYZ');

prompt Question 9 - drafiei
  drop table good_connections;
  create view good_connections (src,dst,dep_date,flightno1,flightno2, layover,price) as
  select a1.src, a2.dst, a1.dep_date, a1.flightno, a2.flightno, a2.dep_time-a1.arr_time,
	min(a1.price+a2.price)
  from available_flights a1, available_flights a2
  where a1.dst=a2.src and a1.arr_time +1.5/24 <=a2.dep_time and a1.arr_time +5/24 >=a2.dep_time
  group by a1.src, a2.dst, a1.dep_date, a1.flightno, a2.flightno, a2.dep_time, a1.arr_time;

  select src, dst, dep_date, flightno1, flightno2, layover/24, price
  from good_connections;

prompt Question 10 - drafiei
  select flightno1, flightno2, layover, price 
  from (
  select flightno1, flightno2, layover, price, row_number() over (order by price asc) rn 
  from 
  (select flightno1, flightno2, layover, price
  from good_connections
  where to_char(dep_date,'DD/MM/YYYY')='22/12/2015' and src='YEG' and dst='LAX'
  union
  select flightno flightno1, '' flightno2, 0 layover, price
  from available_flights
  where to_char(dep_date,'DD/MM/YYYY')='22/12/2015' and src='YEG' and dst='LAX'))
  where rn <=5;
