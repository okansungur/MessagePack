# MessagePack

It's like JSON but fast and small. That is how MessagePack  is described by [MessagePack](https://msgpack.org/). 
It has an easy setup and usage. Decoding for a single operation is faster than json about 1 ms. We can get benefit from MsssagPack especially, when we are working with microservices
Redis, FluentD and Pinterest are the famous companies that use MessagePack.
From their homepage compact = true and schema = 0 are the entries they use for benchmarking. Json encoded version is 27 bytes while MessagePack encoded version is 18 bytes.
It has also a  huge support for programming languages. 
But migrating  from json to message pack we have to be careful as every applications has different conditions and challenges. So we have to see the test results before giving any desicions . 
