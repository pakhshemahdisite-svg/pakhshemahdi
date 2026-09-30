import Foundation

struct APIClient {
    func home() async throws -> HomePayload {
        try await get("home")
    }

    func products() async throws -> ProductsPayload {
        try await get("products?per_page=30")
    }

    func product(id: Int) async throws -> Product {
        try await get("products/\(id)")
    }

    private func get<T: Decodable>(_ path: String) async throws -> T {
        let url = URL(string: path, relativeTo: AppConfig.baseURL)!
        var request = URLRequest(url: url)
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        let (data, response) = try await URLSession.shared.data(for: request)
        guard let http = response as? HTTPURLResponse, 200..<300 ~= http.statusCode else { throw URLError(.badServerResponse) }
        return try JSONDecoder().decode(T.self, from: data)
    }
}
