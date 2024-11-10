package study.backend.studying.src.zbmissions;

/*
 * 제로베이스 백엔드 스쿨 30기
 * 전도명
 *
 * Mission 1 깜짝과제 2. 좌표 거리 계산 프로그램
 *
 */

import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

// 좌표 거리 계산 프로그램
// 사용자의 좌표와 여러 개의 임의 좌표들 간의 거리를 계산하여
// 가장 가까운 좌표를 찾아 출력
public class PointerValue {
    // 사용자 좌표 입력부터 결과 출력까지의 전체 프로세스 관리
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // 사용자 입력을 위한 Scanner 객체 생성
        try {
            // 사용자의 현재 좌표 입력 받기
            System.out.println("내 좌표 x값을 입력해 주세요.");
            int myX = sc.nextInt();
            System.out.println("내 좌표 y값을 입력해 주세요.");
            int myY = sc.nextInt();
            Point myPoint = new Point(myX, myY);  // 사용자의 좌표를 Point 객체로 생성

            int targetCount = 10;  // 입력 좌표 개수 (10개)
            System.out.println("1/" + targetCount + " 번째 입력");
            Set<Point> pointerSet = inputPointer(sc, targetCount);  // 중복 없는 좌표들을 Set으로 저장

            // 가장 가까운 좌표를 찾기 위한 StringBuilder 초기화
            StringBuilder outputBuilder = new StringBuilder();
            Point nearestPoint = findNearestPointer(myPoint, pointerSet, outputBuilder);  // 가장 가까운 좌표 찾기

            // 모든 좌표와의 거리를 출력
            System.out.print(outputBuilder.toString());
            System.out.println("제일 가까운 좌표:");
            System.out.printf("(%d, %d) => %.6f%n", nearestPoint.x, nearestPoint.y, Math.sqrt(distanceSquared(myPoint, nearestPoint)));

        } catch (Exception e) {  // 예외 발생 시 처리
            System.out.println("오류 발생: " + e.getMessage());
        } finally {  // 프로그램 종료 전 Scanner 객체 닫기
            sc.close();
        }
    }

    // 여러 개의 좌표를 입력받아 Set으로 반환하는 메소드
    // 중복된 좌표는 재입력을 요청하고 진행 상황을 실시간으로 출력
    private static Set<Point> inputPointer(Scanner sc, int targetCount) {
        Set<Point> pointerSet = new LinkedHashSet<>();  // 좌표를 저장할 Set 생성

        while (pointerSet.size() < targetCount) {  // 목표 개수만큼 입력될 때까지 반복
            try {
                System.out.println("임의의 좌표 x값을 입력해 주세요.");
                int x = sc.nextInt();
                System.out.println("임의의 좌표 y값을 입력해 주세요.");
                int y = sc.nextInt();

                Point point = new Point(x, y);  // 새로운 좌표 생성

                // 중복 좌표 체크
                if (!pointerSet.add(point)) {  // Set에 추가 실패 == 중복된 좌표
                    System.out.println("동일한 좌표값이 이미 존재합니다. 다시 입력해 주세요.");
                    continue;
                }

                // 다음 입력 안내
                if (pointerSet.size() < targetCount) {
                    System.out.println((pointerSet.size() + 1) + "/" + targetCount + " 번째 입력");
                }

            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
                sc.next();  // 잘못된 입력이 있을 경우 스캐너 초기화
            }
        }

        return pointerSet;  // 모든 좌표가 입력된 Set 반환
    }

    // 주어진 좌표(myPoint)와 가장 가까운 좌표를 찾는 메소드
    // 유클리드 거리 공식을 사용하여 계산
    private static Point findNearestPointer(Point myPoint, Set<Point> pointerSet, StringBuilder outputBuilder) {
        Point nearestPointer = null;
        double minDistance = Double.MAX_VALUE;  // 초기 최소 거리를 최댓값으로 설정

        // 모든 좌표와의 거리를 비교
        for (Point point : pointerSet) {
            double distance = distanceSquared(myPoint, point);  // 제곱된 거리 계산
            outputBuilder.append(String.format("(%d, %d) => %.6f%n", point.x, point.y, Math.sqrt(distance)));  // 거리 계산 결과를 StringBuilder에 추가

            if (distance < minDistance) {  // 더 가까운 좌표 발견 시
                minDistance = distance;  // 최소 거리 갱신
                nearestPointer = point;  // 가장 가까운 좌표 갱신
            }
        }

        return nearestPointer;  // 가장 가까운 좌표 반환
    }

    // 두 점 사이 거리의 제곱을 계산하여 제곱근 연산을 줄임
    private static double distanceSquared(Point p1, Point p2) {
        // x, y 좌표 차이의 제곱을 더하여 거리의 제곱 반환 (제곱근 연산 생략)
        return Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
    }
}