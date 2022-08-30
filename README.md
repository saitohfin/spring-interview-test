# Task API

## Description

We have a simple project which we have Tasks and Users. For now, each task only has one owner.

The goal of this issue is to improve the tasks with more than one user with different roles.
For this issue, we want to keep the role information, but we don't need behaviour for that.

## Goal

Add new endpoint to add members to a task which you could:
* Get members from a task
* Add members for a task
* Only the owner can add members to a task
* Create integration tests for them

## What we are going to review
* Knowledge about Rest in Spring
* Knowledge about JPA
* Api design
* Code design
* Integration tests design

The code for this test has been extracted from a case study, you don't to follow his schema 

## Requirements
* Java 18
* Maven
* Docker

## DB Docker

Run docker-compose up

## Swagger:

Located in port 8081
http://localhost:8081/swagger-ui.html



