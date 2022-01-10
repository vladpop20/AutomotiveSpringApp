# AutomotiveSpringApp


Implemented a small JAVA project for maintaining an entity records in db. It's using Sring Boot and Hibernate for connectig to a Microsoft SQL Server database.
Database schema contains these 3 entities:
- 'Vehicle' with attributes 'VIN' as vehicle identification number, 'DateOfRefistration', and with linkage to Vehicle Type;
- only one single type can be linked;
- 'VehicleType' with attribute 'name' and linkage to Vehicle Part (more parts can be linked to a vehicle type). Example records: 'Commercial Vehicle', 'Passenger Vehicle';
- 'VehiclePart' with attribute 'name'. Example records: 'Tire', 'Hood', 'LED HeadLight';

Basic functionality of the app using endpoints:
- to retrive records from the DB;
- to create new record of any entity;
- to update any individual record;
- to delete specific record;

The app also contains a set of unit and integration tests to validate the implementation.
