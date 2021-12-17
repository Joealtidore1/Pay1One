package com.pay1onet.fmca.models;

public class WalletModel {
    private int id;
    private String agent;
    private double balance;
    private  int agentId;

    public WalletModel(int id, String agent, double balance, int agentId) {
        this.id = id;
        this.agent = agent;
        this.balance = balance;
        this.agentId = agentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }
}
