"# SpaceAgencyDataHub-" 

To start application:
1. git clone https://github.com/MTbliz/SpaceAgencyDataHub-.git
2. open console in folder where you cloned application and run: mvn clean install
3. in console go to 'target' folder and run java -jar spaceagency-0.0.1-SNAPSHOT.jar &


Commends:

0.Add configuration as contentmanager
curl --user contentmanager:123456 -d '' http://localhost:8080/configuration/


CONTENTMANAGER PART

1.Get All missions
curl --user contentmanager:123456 'http://localhost:8080/missions/'

2.Create mission (Sunmission1)
curl --user contentmanager:123456 -d '{"name":"Sunmission1","type":"TYPE_PANCHROMATIC","startDate":"2020-03-10T20:30:52.123Z","finishDate":"2020-03-15T20:30:52.123Z"}' -H "Content-Type: application/json" -X POST http://localhost:8080/missions/

3.Create mission (Sunmission2)
curl --user contentmanager:123456 -d '{"name":"Sunmission2","type":"TYPE_PANCHROMATIC","startDate":"2020-03-10T20:30:52.123Z","finishDate":"2020-03-15T20:30:52.123Z"}' -H "Content-Type: application/json" -X POST http://localhost:8080/missions/

4.Update mission
curl --user contentmanager:123456 -d '{"id":1,"name":"Sunmission1","type":"TYPE_PANCHROMATIC","startDate":"2020-03-10T20:30:52.123Z","finishDate":"2020-03-15T20:30:52.123Z"}' -H "Content-Type: application/json" -X PUT http://localhost:8080/missions/1

5.Delete mission
curl --user contentmanager:123456 -X DELETE http://localhost:8080/missions/1

6.Create prodcut (Create 3 product for the mission with id 2)
curl --user contentmanager:123456 -d '{"mission":{"id":2},"acquisitionDAte":"2020-03-11T20:30:52.123Z","footprint":{"coordinate1":{"latitude":5,"longitude":6},"coordinate2":{"latitude":5,"longitude":8},"coordinate3":{"latitude":4,"longitude":6},"coordinate4":{"latitude":7,"longitude":8}},"price":5000,"url":"sunmissionurl"}' -H "Content-Type: application/json" -X POST http://localhost:8080/products/

7. Get products
curl --user contentmanager:123456 http://localhost:8080/products/

8.Delete product with id 3
curl --user contentmanager:123456 -X DELETE http://localhost:8080/products/3

CUSTOMER PART

9. Search products by Type
curl --user customer:123456 http://localhost:8080/search/products/type?productType=TYPE_PANCHROMATIC

10.Search products by MissionName
curl --user customer:123456 http://localhost:8080/search/products/missionName?missionName=Sunmission2

11.Search products between dates
curl --user customer:123456 'http://localhost:8080/search/products/betweenDates?startDate=2020-03-09T20:30:52.123Z&endDate=2020-03-16T20:30:52.123Z'

12.Search products where date is greater then
curl --user customer:123456 'http://localhost:8080/search/products/greaterThenDate?date=2020-03-09T20:30:52.123Z'

13.Search products where date is less then
curl --user customer:123456 'http://localhost:8080/search/products/lessThenDate?date=2020-03-19T20:30:52.123Z'

14.Search products by coordinate
curl --user customer:123456 'http://localhost:8080/search/products/footprint?latitude=5&longitude=6'


15.Create order
curl --user customer:123456 -d '{"customer":{"id":1},"productList":[{"id":1},{"id":2}]}' -H "Content-Type: application/json" -X POST http://localhost:8080/orders/create

16.Search most ordered product
curl --user customer:123456 'http://localhost:8080/search/products/mostordered'

17.Get orders by customer
curl --user customer:123456 'http://localhost:8080/orders/search/customer?firstName=Jan&lastName=Kowalski'

18.Get All orders (currently not available, to turn on go to the program and change 'dennyAll' for appropraite role)
curl --user contentmanager:123456 'http://localhost:8080/orders'



