-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2023 at 08:53 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `university`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Email` varchar(15) DEFAULT NULL,
  `Password` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Email`, `Password`) VALUES
('admin@gmail.com', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `rollNo` int(11) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `fathersName` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `Date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`rollNo`, `name`, `fathersName`, `status`, `Date`) VALUES
(123, 'Asmaa', 'Saad', 'Present', '2023-05-03'),
(123, 'Asmaa', 'Saad', 'Absent', '2023-05-03'),
(124, 'Ahmed', 'Ali', 'Present', '2023-05-13');

-- --------------------------------------------------------

--
-- Table structure for table `fee`
--

DROP TABLE IF EXISTS `fee`;
CREATE TABLE `fee` (
  `rollNo` int(11) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `FatherName` varchar(20) NOT NULL,
  `Academic_year` varchar(20) NOT NULL,
  `Date` date DEFAULT NULL,
  `pay` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fee`
--

INSERT INTO `fee` (`rollNo`, `name`, `FatherName`, `Academic_year`, `Date`, `pay`) VALUES
(123, 'Asmaa', 'Saad', '2nd', '2023-05-03', 300),
(124, 'Ahmed', 'Ali', '3rd', '2023-05-13', 800);

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

DROP TABLE IF EXISTS `marks`;
CREATE TABLE `marks` (
  `rollNo` int(11) DEFAULT NULL,
  `mark_1` varchar(20) NOT NULL,
  `mark_2` varchar(20) NOT NULL,
  `mark_3` varchar(20) NOT NULL,
  `mark_4` varchar(20) NOT NULL,
  `mark_5` varchar(20) NOT NULL,
  `mark_6` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`rollNo`, `mark_1`, `mark_2`, `mark_3`, `mark_4`, `mark_5`, `mark_6`) VALUES
(123, '9', '7', '5', '4', 'n', 'g'),
(697, 'Excellent', 'Excellent', 'Good', 'Excellent', 'Very Good', 'Very Good'),
(697, 'Excellent', 'Excellent', 'Good', 'Excellent', 'Very Good', 'Very Good');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `name` varchar(10) NOT NULL,
  `fatherName` varchar(10) NOT NULL,
  `age` varchar(3) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(20) NOT NULL,
  `branch` varchar(20) NOT NULL,
  `rollNo` int(11) NOT NULL,
  `password` varchar(10) NOT NULL,
  `acadimicYear` varchar(20) NOT NULL,
  `semester` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`name`, `fatherName`, `age`, `dob`, `address`, `phone`, `email`, `branch`, `rollNo`, `password`, `acadimicYear`, `semester`) VALUES
('Asmaa', 'Saad', '22', '2013-05-03', 'Aswan', '01122334244', 'asmaa@123', 'computer science', 123, '234esw', 'Third year', 'second term'),
('Ahmed', 'Ali', '22', '2002-03-23', 'Aswan', '01122334265', 'ahmed@email.com', 'Power', 124, '124', 'Third year', 'Second term'),
('Esraa', 'Sayed', '22', '2006-05-08', 'Cairo', '01155949040', 'eara@gmail.com', 'Computer Science', 456, '0987', 'thirdYear', 'second'),
('Ali', 'Samy', '18', '1999-06-04', 'Aswan', '01234567976', 'Ali@gmail.com', 'Comunication', 697, '87665', 'fourth year', 'Second term');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
CREATE TABLE `subjects` (
  `rollNo` int(11) DEFAULT NULL,
  `subject_1` varchar(20) NOT NULL,
  `subject_2` varchar(20) NOT NULL,
  `subject_3` varchar(20) NOT NULL,
  `subject_4` varchar(20) NOT NULL,
  `subject_5` varchar(20) NOT NULL,
  `subject_6` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`rollNo`, `subject_1`, `subject_2`, `subject_3`, `subject_4`, `subject_5`, `subject_6`) VALUES
(123, 'l', 'jh', 'h', 'n', 'n', 'n'),
(697, 'Sub_1', 'Sub_2', 'Sub_3', 'Sub_4', 'Sub_5', 'Sub_6'),
(697, 'Sub_1', 'Sub_2', 'Sub_3', 'Sub_4', 'Sub_5', 'Sub_6');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
CREATE TABLE `teachers` (
  `name` varchar(20) NOT NULL,
  `fatherName` varchar(20) NOT NULL,
  `age` int(20) NOT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `department` varchar(20) NOT NULL,
  `rollNo` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `subject` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`name`, `fatherName`, `age`, `dob`, `address`, `phone`, `email`, `department`, `rollNo`, `password`, `subject`) VALUES
('Mohamed', 'Ahmed', 40, '1983-02-22', 'Aswan', '01124563789', 'mohamed@gmail.com', 'Computer Science', '126', '1234', 'subject_3'),
('Ahmed', 'Mohamed', 40, '1856-08-02', 'Cairo', '01547963233', 'ahmed@gmail.com', 'Computer Science', '456987', '456987', 'subject_2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD KEY `rollNo` (`rollNo`);

--
-- Indexes for table `fee`
--
ALTER TABLE `fee`
  ADD KEY `rollNo` (`rollNo`);

--
-- Indexes for table `marks`
--
ALTER TABLE `marks`
  ADD KEY `rollNo` (`rollNo`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`rollNo`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD KEY `rollNo` (`rollNo`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`rollNo`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`rollNo`) REFERENCES `student` (`rollNo`);

--
-- Constraints for table `fee`
--
ALTER TABLE `fee`
  ADD CONSTRAINT `fee_ibfk_1` FOREIGN KEY (`rollNo`) REFERENCES `student` (`rollNo`);

--
-- Constraints for table `marks`
--
ALTER TABLE `marks`
  ADD CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`rollNo`) REFERENCES `subjects` (`rollNo`);

--
-- Constraints for table `subjects`
--
ALTER TABLE `subjects`
  ADD CONSTRAINT `subjects_ibfk_1` FOREIGN KEY (`rollNo`) REFERENCES `student` (`rollNo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
