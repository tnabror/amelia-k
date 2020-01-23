package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IPS {

  public static void main(String[] args) throws IOException, ParseException {
    System.out.println("Pendataan dan Perhitungan IPS (Indeks Prestasi Semester)");
    System.out.println("1. Pendataan Mata Kuliah");
    System.out.println("2. Perhitungan IPS");
    System.out.println("3. Update Grade");
    System.out.println("4. Keluar");
    List<DataKuliah> dataKuliah = new ArrayList<>();
    label:
    while (true) {
      System.out.print("Masukan Pilihan Anda : ");
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input = br.readLine();
      System.out.println();
      switch (input) {
        case "1":
          dataKuliah = pendataanMataKuliah();
          break;
        case "2":
          perhitunganIPS(dataKuliah);
          break;
        case "3":
          updateGrade(dataKuliah);
          break;
        case "4":
          System.out.println("Terimakasih :)");
          break label;
        default:
          System.out.println("mohon input pilihan yang benar!!!");
      }
    }
  }

  //menu pendataan mata kuliah
  public static List<DataKuliah> pendataanMataKuliah() throws IOException {
    System.out.println("*** Pendataan Mata Kuliah ***");
    System.out.print("Masukan Jumlah Matakuliah : ");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    int inputUser = Integer.parseInt(input);

    List<DataKuliah> list = new ArrayList<>();

    for (int i = 0; i < inputUser; i++) {
      DataKuliah dataKuliah = new DataKuliah();

      System.out.print("\nMasukan Kode Matakuliah : ");
      dataKuliah.kodeMataKuliah = br.readLine();

      System.out.print("Masukan Nama Matakuliah : ");
      dataKuliah.namaMataKuliah = br.readLine();

      dataKuliah.gradeMataKuliah = checkValidGrade();

      System.out.print("Masukan Jumlah SKS : ");
      dataKuliah.jumlahSks = Integer.valueOf(br.readLine());

      //add to list
      list.add(dataKuliah);
    }
    System.out.println("\n*** input selesai ***");

    return list;
  }

  //menu perhitungan IPS
  public static void perhitunganIPS(List<DataKuliah> dataKuliah) throws ParseException {
    if (dataKuliah.size() == 0) {
      System.out.println(
          "DATA MATAKULIAH KOSONG : mohon lakukan pendataan mata kuliah terlebih dahulu!!\n");
    } else {
      System.out.println("*** Perhitungan IPS ***");
      System.out.println("Matakuliah yang di ambil adalah :\n");
      System.out.format("%10s%25s%15s%10s%10s", "Kode", "Nama", "Grade", "Nilai", "SKS");
      System.out.println();
      printTableMataKuliah(dataKuliah);
      System.out.println("\nNilai IPS anda adalah: " + countIPS(dataKuliah));
      System.out.println();
      System.out.println("*** end perhitungan IPS ***");
    }
  }

  // menu update grade
  public static void updateGrade(List<DataKuliah> dataKuliah) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("*** Update Grade ***\n");
    System.out.print("Masukan Kode Matakuliah : ");
    String kodeMatakUliah = br.readLine();
    while (true) {
      if (checkMataKuliah(kodeMatakUliah, dataKuliah)) {
        break;
      } else {
        System.out.println("Kode Matakuliah tidak ditemukan!!!\n");
        System.out.print("Masukan Kode Matakuliah : ");
        kodeMatakUliah = br.readLine();
      }
    }

    String newGrade = checkNewGrade();

    for (DataKuliah data : dataKuliah) {
      if (data.kodeMataKuliah.equals(kodeMatakUliah)) {
        data.gradeMataKuliah = newGrade;
      }
    }
    System.out.println();
    System.out.format("%10s%25s%15s%10s%10s", "Kode", "Nama", "Grade", "Nilai", "SKS");
    System.out.println();
    printTableMataKuliah(dataKuliah);
    System.out.println("\n*** end update grade ***");
  }

  // print table mata kuliah
  public static void printTableMataKuliah(List<DataKuliah> dataKuliah) {
    for (DataKuliah kuliah : dataKuliah) {
      String kode = kuliah.kodeMataKuliah;
      String nama = kuliah.namaMataKuliah;
      String grade = kuliah.gradeMataKuliah;
      String sks = String.valueOf(kuliah.jumlahSks);
      System.out.format("%10s%25s%15s%10s%10s", kode, nama, grade, getNilaiByGrade(grade), sks);
      System.out.println();
    }
  }

  //cek mata kuliah untuk update grade, klu mata kuliah tidak di temukan akan return false
  public static boolean checkMataKuliah(String kode, List<DataKuliah> dataKuliah) {
    for (DataKuliah data : dataKuliah) {
      if (data.kodeMataKuliah.equals(kode)) {
        return true;
      }
    }
    return false;
  }

  //cek apakah grade yang di masukan A, B, C, D, E kalau salah bakal di looping sampek benar inputnya
  public static String checkValidGrade() throws IOException {
    List<String> grades = Arrays.asList("A", "B", "C", "D", "E");
    while (true) {
      System.out.print("Masukan Grade Matakuliah : ");
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input = br.readLine();
      if (grades.contains(input)) {
        return input;
      } else {
        System.out.println("\nERROR : Please input valid Grade! \n");
      }
    }
  }

  // check grade yang di input benar apa enggak, klu gak benar bakal input ulang grade sampek benar
  public static String checkNewGrade() throws IOException {
    List<String> grades = Arrays.asList("A", "B", "C", "D", "E");
    while (true) {
      System.out.print("Masukan Grade Baru : ");
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input = br.readLine();
      if (grades.contains(input)) {
        return input;
      } else {
        System.out.println("\nERROR : Please input valid Grade! \n");
      }
    }
  }

  //menghitung total IPS
  public static double countIPS(List<DataKuliah> dataKuliah) throws ParseException {
    double totalSks = 0;
    double nilaiPerMataKuliah = 0;
    for (DataKuliah kuliah : dataKuliah) {
      nilaiPerMataKuliah = nilaiPerMataKuliah + (kuliah.jumlahSks * getNilaiByGrade(
          kuliah.gradeMataKuliah));
      totalSks = totalSks + kuliah.jumlahSks;
    }

    DecimalFormat df = new DecimalFormat("0.00");
    String formatDouble = df.format(nilaiPerMataKuliah / totalSks);

    return (double) (Double) df.parse(formatDouble);
  }

  // menghitung nilai dari grade yang di masukan
  public static int getNilaiByGrade(String grade) {
    switch (grade) {
      case "A":
        return 4;
      case "B":
        return 3;
      case "C":
        return 2;
      case "D":
        return 1;
      case "E":
        return 0;
      default:
        return 0;
    }
  }

  // class untuk menampung data mata kuliah
  public static class DataKuliah {

    private String kodeMataKuliah;
    private String namaMataKuliah;
    private String gradeMataKuliah;
    private Integer jumlahSks;
  }
}
