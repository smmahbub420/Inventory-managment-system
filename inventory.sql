-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2018 at 09:44 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `cat_id` int(10) NOT NULL,
  `cat_name` varchar(30) NOT NULL,
  `subcat_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`cat_id`, `cat_name`, `subcat_name`) VALUES
(1, 'foods', 'beverage'),
(2, 'foods', 'snacks'),
(3, 'electronics', 'laptop'),
(4, 'electronics', 'television'),
(5, 'electronics', 'refrigerator'),
(6, 'foods', 'bakery'),
(7, 'men_fashion', 'shirts'),
(8, 'men_fashion', 'pants'),
(9, 'jewelry', 'finger_ring'),
(10, 'jewelry', 'ear_ring'),
(11, 'watch', 'hand_watch'),
(12, 'watch', 'wall_watch'),
(13, 'others', 'baby product'),
(14, 'medicine', 'tablet'),
(15, 'electronics', 'AC'),
(16, 'electronics', 'mobile');

-- --------------------------------------------------------

--
-- Table structure for table `dept`
--

CREATE TABLE `dept` (
  `deptno` int(10) NOT NULL,
  `position` varchar(26) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dept`
--

INSERT INTO `dept` (`deptno`, `position`) VALUES
(1, 'admin'),
(2, 'manager'),
(3, 'salesman');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `customer_name` varchar(30) NOT NULL,
  `invoice_date` varchar(30) NOT NULL,
  `invoice_no` int(15) NOT NULL,
  `pid` int(30) NOT NULL,
  `pname` varchar(30) NOT NULL,
  `buying_quantity` int(10) NOT NULL,
  `price` double NOT NULL,
  `total_price` double NOT NULL,
  `invoice_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`customer_name`, `invoice_date`, `invoice_no`, `pid`, `pname`, `buying_quantity`, `price`, `total_price`, `invoice_id`) VALUES
('sayed', '2018/12/18', 8474621, 1, 'dew', 2, 25, 50, 23),
('sayed', '2018/12/18', 8474621, 1, 'dew', 2, 25, 50, 24),
('adnan', '2018/12/18', 9076034, 1, 'mountain dew', 4, 25, 100, 25),
('samuell', '2018/12/18', 2897173, 1, 'mountain dew', 1, 25, 25, 26),
('samuell', '2018/12/18', 2897173, 1, 'mountain dew', 1, 25, 25, 27),
('mehedi', '2018/12/18', 1529932, 1, 'mountain dew', 1, 25, 25, 28),
('mehedi', '2018/12/18', 1529932, 1, 'mountain dew', 1, 25, 25, 29),
('mahbub', '2018/12/18', 1105168, 1, 'mountain dew', 1, 25, 25, 30),
('mahbub', '2018/12/18', 1105168, 1, 'mountain dew', 1, 25, 25, 31),
('adnan', '2018/12/18', 7456474, 1, 'mountain dew', 1, 25, 25, 32),
('adnan', '2018/12/18', 7456474, 1, 'mountain dew', 1, 25, 25, 33),
('adnan', '2018/12/18', 2724905, 1, 'mountain dew', 1, 25, 25, 34),
('adnan', '2018/12/18', 2724905, 1, 'mountain dew', 1, 25, 25, 35),
('sjskc', '2018/12/18', 5921031, 1, 'mountain dew', 1, 25, 25, 36),
('sjskc', '2018/12/18', 5921031, 1, 'mountain dew', 1, 25, 25, 37),
('sdfg', '2018/12/18', 4337096, 1, 'mountain dew', 1, 25, 25, 38),
('abir', '2018/12/18', 7970722, 1, 'mountain dew', 10, 25, 250, 39),
('abir', '2018/12/18', 7970722, 14, 'cheeses', 5, 70, 350, 40),
('abir', '2018/12/18', 3058406, 1, 'mountain dew', 5, 25, 125, 41),
('rafe', '2018/12/18', 8214902, 12, 'haier', 1, 60000, 60000, 42);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `pid` int(30) NOT NULL,
  `pname` varchar(30) NOT NULL,
  `quantity` int(11) NOT NULL,
  `buying_price` double NOT NULL,
  `selling_price` double NOT NULL,
  `incoming_date` varchar(30) NOT NULL,
  `subcat_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`pid`, `pname`, `quantity`, `buying_price`, `selling_price`, `incoming_date`, `subcat_name`) VALUES
(1, 'mountain dew', 68, 22, 25, '2018-11-30', 'beverage'),
(12, 'haier', 9, 40000, 60000, '2018/12/18', 'AC'),
(13, 'dinner rolls', 100, 45, 60, '2018/12/18', 'bakery'),
(14, 'cheeses', 20, 45, 70, '2018/12/18', 'bakery'),
(15, 'dell insprion', 35, 50000, 65000, '2018/12/18', 'laptop'),
(16, 'walton', 45, 32000, 40000, '2018/12/18', 'refrigerator'),
(17, 'acer', 30, 45000, 60000, '2018/12/18', 'laptop'),
(18, 'sony tv', 40, 40000, 65000, '2018/12/18', 'laptop'),
(19, 'xperia X', 100, 20000, 25000, '2018/12/18', 'mobile');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(26) NOT NULL,
  `uid` int(10) NOT NULL,
  `password` varchar(26) NOT NULL,
  `deptno` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `uid`, `password`, `deptno`) VALUES
('sayed', 2, 'sayed', 1),
('manager', 3, 'manager', 2),
('mahbub', 4, 'mahbub', 1),
('admin', 9, 'admin', 1),
('adnan', 10, 'adu', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cat_id`),
  ADD UNIQUE KEY `subcat_name` (`subcat_name`);

--
-- Indexes for table `dept`
--
ALTER TABLE `dept`
  ADD PRIMARY KEY (`deptno`),
  ADD UNIQUE KEY `deptno` (`deptno`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoice_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`pid`),
  ADD UNIQUE KEY `pid` (`pid`),
  ADD KEY `subcat_name` (`subcat_name`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `uid` (`uid`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `deptno` (`deptno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `cat_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoice_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `pid` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`subcat_name`) REFERENCES `category` (`subcat_name`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`deptno`) REFERENCES `dept` (`deptno`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
