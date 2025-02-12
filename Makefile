start:
	docker-compose -f docker-compose.yml up
deploy:
	docker-compose -f docker-compose.yml up -d --build
undeploy:
	docker-compose -f docker-compose.yml down -v
stop:
	docker-compose -f docker-compose.yml stop