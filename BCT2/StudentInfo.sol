//SPDX-License-Identifier: UNLicensed
pragma solidity ^0.8.19;

contract StudentInfo{
    struct Student{
        uint studentid;
        string name;
        uint grade;
    }

    Student[] public students;

    mapping(uint=>bool)private studentExist;

    event StudentAdded(uint indexed studentid, string name, uint grade);

    event EtherReceived(address indexed sender, uint value);

    function addStudent(uint _studentid, string memory _name, uint _grade) public{
        require(!studentExist[_studentid], "Student ID already exists");

        students.push(Student(_studentid, _name, _grade));

        studentExist[_studentid]= true;

        emit StudentAdded(_studentid, _name, _grade);
    }

    function getstudentcount() public view returns(uint){
        return students.length;
    }

    receive() external payable{
        emit EtherReceived(msg.sender, msg.value);
    }

    fallback() external payable{
        emit EtherReceived(msg.sender, msg.value);
    }
}