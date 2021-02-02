# PBM-Converter
A PBM-Converter written in Java

# Features
- Conversion of many normal formats to PBM
- Dithering
- Write Files (Or print to console if needed)
- Practical and simple Java API
# How to use in console
You can use -h or -help to get an overview of the commands.
Here are some examples:

*Convert a .png file to .pbm*
```
converter.jar picture.png picture.pbm
```
*Convert and print to console*
```
converter.jar picture.png -print
```
*Use Dithering*
```
converter.jar picture.png picture.pbm -dith
```

# Use as API

Use the ```Writer``` class for this. It has two functions, ```writePBM``` and ```writeDithPBM```.
```writePBM``` does not use dithering ```writeDithPBM``` does. To get a better example look into the source code of the converter.
