-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 10, 2025 at 07:25 AM
-- Server version: 8.4.3
-- PHP Version: 8.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `konserdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `konser`
--

CREATE TABLE `konser` (
  `id` int NOT NULL,
  `nama_konser` varchar(100) NOT NULL,
  `tanggal` date NOT NULL,
  `venue` varchar(100) NOT NULL,
  `artis` varchar(100) DEFAULT NULL,
  `jenis` varchar(20) NOT NULL DEFAULT 'konser',
  `kapasitas` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `konser`
--

INSERT INTO `konser` (`id`, `nama_konser`, `tanggal`, `venue`, `artis`, `jenis`, `kapasitas`) VALUES
(3, 'PBO', '2000-03-02', 'LAB', 'GACOR', 'konser', NULL),
(4, 'Music Fest 2025', '2025-11-15', 'Jakarta Stadium', 'Raisa', 'konser', NULL),
(5, 'Java Music Fest', '2025-11-10', 'Jakarta International Stadium', 'Raisa', 'konser', NULL),
(6, 'Indie Harmony Night', '2025-12-05', 'Bandung Creative Hall', 'Pamungkas', 'konser', NULL),
(7, 'Private Acoustic Evening', '2025-12-20', 'Bali Beach Resort', NULL, 'private', 50),
(8, 'Rock Explosion', '2026-01-15', 'Surabaya Dome', 'NOAH', 'konser', NULL),
(9, 'Luxury Private Gala', '2026-02-02', 'Yogyakarta Heritage Hotel', NULL, 'private', 30);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `konser`
--
ALTER TABLE `konser`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `konser`
--
ALTER TABLE `konser`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
