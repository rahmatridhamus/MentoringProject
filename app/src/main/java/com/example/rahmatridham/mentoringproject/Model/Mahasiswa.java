package com.example.rahmatridham.mentoringproject.Model;

/**
 * Created by rahmatridham on 10/21/2016.
 */

public class Mahasiswa {
    String Nama, alamat, noTelpon, NIM, jenisKelamin;


    public Mahasiswa(String nama, String alamat, String noTelpon, String NIM, String jenisKelamin) {
        Nama = nama;
        this.alamat = alamat;
        this.noTelpon = noTelpon;
        this.NIM = NIM;
        this.jenisKelamin = jenisKelamin;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "Nama='" + Nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", noTelpon='" + noTelpon + '\'' +
                ", NIM='" + NIM + '\'' +
                ", jenisKelamin='" + jenisKelamin + '\'' +
                '}';
    }
}
