--
-- PostgreSQL database dump
--

-- Dumped from database version 13.16 (Debian 13.16-0+deb11u1)
-- Dumped by pg_dump version 13.16 (Debian 13.16-0+deb11u1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Boarding; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."Boarding" (
    "Id" bigint NOT NULL,
    "PassengerId" text NOT NULL,
    "PassengerType" text,
    "BoardingTime" timestamp without time zone NOT NULL,
    "BusStopId" text NOT NULL,
    "Latitude" double precision,
    "Longitude" double precision,
    "TripId" text NOT NULL,
    "BoardingType" smallint
);


ALTER TABLE public."Boarding" OWNER TO kentkart;

--
-- Name: BusStop; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."BusStop" (
    "Id" text NOT NULL,
    "Latitude" double precision NOT NULL,
    "Longitude" double precision NOT NULL,
    "StopName" text NOT NULL
);


ALTER TABLE public."BusStop" OWNER TO kentkart;

--
-- Name: Direction; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."Direction" (
    "Id" smallint NOT NULL,
    "Name" text NOT NULL
);


ALTER TABLE public."Direction" OWNER TO kentkart;

--
-- Name: Line; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."Line" (
    "Id" bigint NOT NULL,
    "LineCode" text NOT NULL,
    "LineCodeRepresentation" text NOT NULL
);


ALTER TABLE public."Line" OWNER TO kentkart;

--
-- Name: Route; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."Route" (
    "Id" text NOT NULL,
    "LineId" bigint NOT NULL,
    "DirectionId" smallint NOT NULL
);


ALTER TABLE public."Route" OWNER TO kentkart;

--
-- Name: Trip; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."Trip" (
    "Id" text NOT NULL,
    "BusId" text NOT NULL,
    "RouteId" text NOT NULL,
    "TripStartTime" timestamp without time zone NOT NULL,
    "TripEndTime" timestamp without time zone NOT NULL
);


ALTER TABLE public."Trip" OWNER TO kentkart;

--
-- Name: BoardingTripView; Type: VIEW; Schema: public; Owner: kentkart
--

CREATE VIEW public."BoardingTripView" WITH (security_barrier='false') AS
 SELECT b."PassengerId",
    b."PassengerType",
    b."BoardingTime",
    b."Latitude" AS "BoardingLatitude",
    b."Longitude" AS "BoardingLongitude",
    t."BusId",
    t."Id" AS "TripId",
    t."TripStartTime",
    t."TripEndTime",
    r."LineId",
    l."LineCode",
    l."LineCodeRepresentation",
    r."DirectionId" AS "Direction",
    t."RouteId",
    b."BusStopId",
    bs."Latitude" AS "BusStopLatitude",
    bs."Longitude" AS "BusStopLongitude",
    bs."StopName" AS "BusStopName",
    b."Id"
   FROM (((((public."Boarding" b
     LEFT JOIN public."Trip" t ON ((b."TripId" = t."Id")))
     LEFT JOIN public."BusStop" bs ON ((b."BusStopId" = bs."Id")))
     LEFT JOIN public."Route" r ON ((t."RouteId" = r."Id")))
     LEFT JOIN public."Line" l ON ((r."LineId" = l."Id")))
     LEFT JOIN public."Direction" d ON ((r."DirectionId" = d."Id")));


ALTER TABLE public."BoardingTripView" OWNER TO kentkart;

--
-- Name: BoardingType; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."BoardingType" (
    "Id" smallint NOT NULL,
    "Name" text NOT NULL
);


ALTER TABLE public."BoardingType" OWNER TO kentkart;

--
-- Name: BusStopOfRoute; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."BusStopOfRoute" (
    "Id" bigint NOT NULL,
    "RouteId" text NOT NULL,
    "BusStopId" text NOT NULL,
    "Sequence" integer NOT NULL,
    "ArrivalOffset" bigint,
    "DepartureOffset" bigint
);


ALTER TABLE public."BusStopOfRoute" OWNER TO kentkart;

--
-- Name: Departure; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."Departure" (
    "Id" bigint NOT NULL,
    "RouteId" text NOT NULL,
    "DepartureDayId" smallint NOT NULL,
    "DepartureTime" text NOT NULL,
    "ScheduleId" bigint
);


ALTER TABLE public."Departure" OWNER TO kentkart;

--
-- Name: DepartureDay; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."DepartureDay" (
    "Id" smallint NOT NULL,
    "Day" text NOT NULL
);


ALTER TABLE public."DepartureDay" OWNER TO kentkart;

--
-- Name: OrphanBoarding; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."OrphanBoarding" (
    "Id" bigint NOT NULL,
    "PassengerId" text NOT NULL,
    "PassengerType" text,
    "BusId" text NOT NULL,
    "BoardingTime" timestamp without time zone NOT NULL,
    "LineId" bigint NOT NULL,
    "BusStopId" text NOT NULL,
    "BoardingType" smallint
);


ALTER TABLE public."OrphanBoarding" OWNER TO kentkart;

--
-- Name: OrphanBoardingDetailedView; Type: VIEW; Schema: public; Owner: kentkart
--

CREATE VIEW public."OrphanBoardingDetailedView" WITH (security_barrier='false') AS
 SELECT ob."PassengerId",
    ob."PassengerType",
    ob."BoardingTime",
    ob."BusId",
    ob."LineId",
    l."LineCode",
    l."LineCodeRepresentation",
    ob."BusStopId",
    bs."Latitude" AS "BusStopLatitude",
    bs."Longitude" AS "BusStopLongitude",
    bs."StopName" AS "BusStopName",
    ob."Id"
   FROM ((public."OrphanBoarding" ob
     LEFT JOIN public."Line" l ON ((ob."LineId" = l."Id")))
     LEFT JOIN public."BusStop" bs ON ((ob."BusStopId" = bs."Id")));


ALTER TABLE public."OrphanBoardingDetailedView" OWNER TO kentkart;

--
-- Name: Point; Type: TABLE; Schema: public; Owner: kentkart
--

CREATE TABLE public."Point" (
    "Id" bigint NOT NULL,
    "RouteId" text NOT NULL,
    "Sequence" integer NOT NULL,
    "Latitude" double precision NOT NULL,
    "Longitude" double precision NOT NULL
);


ALTER TABLE public."Point" OWNER TO kentkart;

--
-- Name: BoardingType BoardingType_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."BoardingType"
    ADD CONSTRAINT "BoardingType_pkey" PRIMARY KEY ("Id");


--
-- Name: Boarding Boarding_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Boarding"
    ADD CONSTRAINT "Boarding_pkey" PRIMARY KEY ("Id");


--
-- Name: BusStopOfRoute BusStopOfRoute_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."BusStopOfRoute"
    ADD CONSTRAINT "BusStopOfRoute_pkey" PRIMARY KEY ("Id");


--
-- Name: BusStop BusStop_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."BusStop"
    ADD CONSTRAINT "BusStop_pkey" PRIMARY KEY ("Id");


--
-- Name: DepartureDay DepartureDay_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."DepartureDay"
    ADD CONSTRAINT "DepartureDay_pkey" PRIMARY KEY ("Id");


--
-- Name: Departure Departure_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Departure"
    ADD CONSTRAINT "Departure_pkey" PRIMARY KEY ("Id");


--
-- Name: Direction Direction_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Direction"
    ADD CONSTRAINT "Direction_pkey" PRIMARY KEY ("Id");


--
-- Name: Line Line_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Line"
    ADD CONSTRAINT "Line_pkey" PRIMARY KEY ("Id");


--
-- Name: OrphanBoarding OrphanBoarding_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."OrphanBoarding"
    ADD CONSTRAINT "OrphanBoarding_pkey" PRIMARY KEY ("Id");


--
-- Name: Point Point_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Point"
    ADD CONSTRAINT "Point_pkey" PRIMARY KEY ("Id");


--
-- Name: Route Route_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Route"
    ADD CONSTRAINT "Route_pkey" PRIMARY KEY ("Id");


--
-- Name: Trip Trip_pkey; Type: CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Trip"
    ADD CONSTRAINT "Trip_pkey" PRIMARY KEY ("Id");


--
-- Name: Boarding Boarding_BusStopId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Boarding"
    ADD CONSTRAINT "Boarding_BusStopId_fkey" FOREIGN KEY ("BusStopId") REFERENCES public."BusStop"("Id");


--
-- Name: Boarding Boarding_TripId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Boarding"
    ADD CONSTRAINT "Boarding_TripId_fkey" FOREIGN KEY ("TripId") REFERENCES public."Trip"("Id");


--
-- Name: BusStopOfRoute BusStopOfRoute_BusStopId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."BusStopOfRoute"
    ADD CONSTRAINT "BusStopOfRoute_BusStopId_fkey" FOREIGN KEY ("BusStopId") REFERENCES public."BusStop"("Id") NOT VALID;


--
-- Name: BusStopOfRoute BusStopOfRoute_RouteId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."BusStopOfRoute"
    ADD CONSTRAINT "BusStopOfRoute_RouteId_fkey" FOREIGN KEY ("RouteId") REFERENCES public."Route"("Id") NOT VALID;


--
-- Name: Departure Departure_DepartureDayId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Departure"
    ADD CONSTRAINT "Departure_DepartureDayId_fkey" FOREIGN KEY ("DepartureDayId") REFERENCES public."DepartureDay"("Id");


--
-- Name: Departure Departure_RouteId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Departure"
    ADD CONSTRAINT "Departure_RouteId_fkey" FOREIGN KEY ("RouteId") REFERENCES public."Route"("Id");


--
-- Name: OrphanBoarding OrphanBoarding_LineId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."OrphanBoarding"
    ADD CONSTRAINT "OrphanBoarding_LineId_fkey" FOREIGN KEY ("LineId") REFERENCES public."Line"("Id");


--
-- Name: Point Point_RouteId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Point"
    ADD CONSTRAINT "Point_RouteId_fkey" FOREIGN KEY ("RouteId") REFERENCES public."Route"("Id");


--
-- Name: Route Route_DirectionId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Route"
    ADD CONSTRAINT "Route_DirectionId_fkey" FOREIGN KEY ("DirectionId") REFERENCES public."Direction"("Id");


--
-- Name: Route Route_LineId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Route"
    ADD CONSTRAINT "Route_LineId_fkey" FOREIGN KEY ("LineId") REFERENCES public."Line"("Id");


--
-- Name: Trip Trip_RouteId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kentkart
--

ALTER TABLE ONLY public."Trip"
    ADD CONSTRAINT "Trip_RouteId_fkey" FOREIGN KEY ("RouteId") REFERENCES public."Route"("Id");


--
-- PostgreSQL database dump complete
--

