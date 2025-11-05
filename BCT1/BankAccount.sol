// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.19;

contract BankAccount{
    uint public balance;
    address public owner;

    event Deposit(address indexed account, uint amount);
    event Withdrawal(address indexed account, uint amount);

    constructor(){
        owner = msg.sender;
    }

    modifier onlyOwner(){
        require(msg.sender == owner, "You are not the owner of this account");
        _;
    }

    function deposit() public payable onlyOwner{
        require(msg.value > 0, "Deposit amount must be greater than zero");
        balance += msg.value;
        emit Deposit(msg.sender, msg.value);
    }

    function withdraw(uint _amount) public payable onlyOwner{
        require(balance >= _amount, "Insufficient balance to withdraw");
        require(_amount >= 0, "Withdraw amount must be greater than zero");

        balance -= _amount;

        (bool success, )=owner.call{value:_amount}("");
        require(success, "Falied to send Ether");

        emit Withdrawal(msg.sender, _amount);
    }

    function getbalance() public view returns (uint){
        return balance;
    }
}