start:
	docker-compose -f docker-compose.yml up -d
deploy:
	docker-compose -f docker-compose.yml up --build -d
undeploy:
	docker-compose -f docker-compose.yml down
stop:
	docker-compose -f docker-compose.yml stop