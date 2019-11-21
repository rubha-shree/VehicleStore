# VehicleStore

1) Implement a Vehicle Store, the entry point

2) Vehicle Store would have Vehicle

3) Vehicle would have Vehicle Configuration

4) Vehicle Configuration would be for Make, Model, Registration Number, Seat, Transmission, Wheel, Start Mode, Color, Speed Limit

5) For now we would have only Four wheeler vehicle configuration

6) Vehicle Store would have Warehouse: Old and New Vehicle Warehouse.

7) Build command line tool, that would let add a new Vehicle and its configuration (after asking inputs on the configuration on command line) to the one of the selected warehouse.

8) Vehicle would have: start, stop, accelerate to km/hr, accelerate by percentage behavior

9) On configuration error a typed error need to be thrown, but command line tool should not exit.

10) On over speed, Over Speed Error need to be throw, but command line tool should not exit.

11) We can select a vehicle from selected warehouse by registration number or some pattern in registration number. If multiple match, it should print all numbers and ask for which one to do operation, as mentioned in 8.

Idea:

1) Use Enum

2) Use collections

3) Declare, throw and catch exceptions

4) Use generics

5) Implement hashCode, equals, toString()

6) Use regular expression to find vehicle with pattern for registration number.
