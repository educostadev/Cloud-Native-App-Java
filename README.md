# Cloud-Native-App-Java
Playground for exercises from the book Cloud Native Application in Java. The project template was create using the [Spring Initilizr Web Site](https://start.spring.io/).

It´s use the `Eureka registry` from `String Cloud Netflix` as service registry.

## Projects

- `eureka-server`: Project that run the service registry
- `product`: Product service. It´s registry itself on service registry
- `product-client`: It's a client of `product` service. It's automatically lookup for the `product` service on service registry use it. On the book the client classes are added in the same project of the product service.

