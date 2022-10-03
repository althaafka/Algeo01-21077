# Tugas Besar 1 [IF2123] Aljabar Linier dan Geometri
## Kelompok Oegla:
1. Husnia Munzayana         – 13521077
2. Shelma Salsabila         – 13521115 
3. Althaaf Khasyi Atisomya  – 13521130 

## Deskripsi Program
Program ini dibuat untuk memenuhi Tugas Besar Mata Kuliah IF2123 Aljabar Linier dan Geometri. Program dibuat dengan Bahasa Java. Program ini digunakan untuk menyelesaikan persoalan :
1. Penyelesaian Sistem Persamaan Linier dengan menggunakan metode eliminasi Gauss, metode Eliminasi Gauss-Jordan, metode matriks balikan,dan kaidah Cramer,
2. Menghitung determinan matriks dengan metode reduksi baris dan ekspansi kofaktor,
3. Menghitung balikan(_inverse_) matriks dengan metode eliminasi Gauss-Jordan dan ekspansi kofaktor,
4. Menentukan persamaan dan estimasi nilai fungsi dengan interpolasi polinom,
5. Menyelesaikan persoalan interpolasi _bicubic_, serta
6. Menentukan persamaan regresi linier berganda dan menaksir nilai fungsinya.

Pada repository ini terdapat enam folder:
1. Folder _'bin'_ berisi _java bytecode_ (*.class)
2. Folder _'src'_ berisi _source code_ dari program Java
3. Folder _'test'_ berisi data uji
4. Folder _'doc'_ berisi laporan
5. Folder _'lib'_ berisi library program java dalam .jar
6. Folder _'test'_ berisi test case input dan output (output file tersimpan di `test/output`)

## Cara menjalankan program
### Install tools yang dibutuhkan 
Sebelum menjalankan program, perlu untuk menginstall tools:
1. Java  : https://www.java.com/en/download/
2. Java Development Kit(JDK)  : https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

### Eksekusi Program
1. Clone repositori dari git : 
    ```
    git clone https://github.com/althaafka/Algeo01-21077.git
    ```
2. Alternatif cara menjalankan program:

   a. Menggunakan command line dengan mengetikkan : 
        ```
        cd src
        javac -d ../bin ./*.java
        cd ../bin
        java Main
        ```
        
   b. Menggunakan file .jar
        ```
        cd lib
        java -jar Algeo01-21077.jar
        ```
### Catatan
> Untuk input program berupa file, file input disimpan di folder test sehingga saat melakukan input file cukup mengetikkan nama file dan diakhiri '.txt' (tidak perlu menuliskan path file input).

> Untuk output hasil program berupa file disimpan pada ../test/output sehingga saat mengetikkan nama file output cukup mengetikkan nama file dan diakhiri '.txt' (tidak perlu menuliskan path file output)
