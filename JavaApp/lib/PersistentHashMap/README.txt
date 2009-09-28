A file system backed HashMap. The PersistentHashMap, is an extension for HashMap, which can spill over to the file system. It is particularly suitable for large sized HashMaps which cannot be completely retained in memory. Since PersistentHashMap is file system backed, any excess entries, over the specified number of maximum entries allowed in map, are written to the backing files.Internally, the PersistentHashMap maintains list of backing files, each one of those is a serialized HashMap written onto a backing file. So in a way, a PersistentHashMap is a container for multiple HashMaps.

Usage:
1.Add PersistentHashMap.jar to your project's classpath.
2.Refer apidocs for api details.

Please post any queries/bugs to m.satyadeep@gmail.com