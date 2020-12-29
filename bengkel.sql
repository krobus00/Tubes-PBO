-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 29 Des 2020 pada 04.55
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bengkel`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `kendaraan`
--

CREATE TABLE `kendaraan` (
  `id` int(11) NOT NULL,
  `nomor` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis` enum('motor','mobil') NOT NULL,
  `keterangan` longtext NOT NULL,
  `idpemilik` int(11) NOT NULL,
  `masuk` timestamp NOT NULL DEFAULT current_timestamp(),
  `keluar` timestamp NULL DEFAULT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kerusakan`
--

CREATE TABLE `kerusakan` (
  `id` int(11) NOT NULL,
  `idkendaraan` int(11) NOT NULL,
  `idmontir` int(11) NOT NULL,
  `bagian` varchar(255) NOT NULL,
  `keterangan` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `biaya` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `sparepart`
--

CREATE TABLE `sparepart` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `merk` varchar(255) NOT NULL,
  `bagian` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `sparepart`
--

INSERT INTO `sparepart` (`id`, `nama`, `merk`, `bagian`, `harga`, `jumlah`) VALUES
(6, 'Velg Jazz', 'honda', 'roda', 500000, 98),
(7, 'Ban jazz', 'honda', 'roda', 200000, 215),
(8, 'Lampu depan', 'honda', 'lampu', 50000, 49999);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(18) NOT NULL,
  `password` varchar(30) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `level` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `nama`, `username`, `password`, `alamat`, `level`) VALUES
(1, 'Michael Putera Wardana', 'michael', 'asd', 'jl asdasd asdasd', 'admin'),
(2, 'Yogi', 'yogi', 'asd', 'dasdasd', 'pelanggan'),
(4, 'Iqbal', 'Iqbal', 'asd', 'asdasdasd', 'montir'),
(5, 'Jamet111', 'jamet02', '123', 'jl jamet ini bous', 'montir'),
(6, 'pelanggan', 'pelanggan', 'asd', 'asdasd', 'montir');

-- --------------------------------------------------------

--
-- Struktur dari tabel `voucher`
--

CREATE TABLE `voucher` (
  `id` int(11) NOT NULL,
  `kode` varchar(255) NOT NULL,
  `persen` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `kendaraan`
--
ALTER TABLE `kendaraan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `kerusakan`
--
ALTER TABLE `kerusakan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `sparepart`
--
ALTER TABLE `sparepart`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `voucher`
--
ALTER TABLE `voucher`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `kendaraan`
--
ALTER TABLE `kendaraan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `kerusakan`
--
ALTER TABLE `kerusakan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `sparepart`
--
ALTER TABLE `sparepart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `voucher`
--
ALTER TABLE `voucher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
