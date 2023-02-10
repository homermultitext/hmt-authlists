using StringDistances
f = joinpath(pwd(), "data", "hmtnames.cex")

namelist = map(readlines(f)[3:end]) do ln
    split(ln, "|")[4]
end

function bestmatch(n, authlist)
    max = 0
    maxname = ""
    for item in authlist
        comp = compare(n, item, Levenshtein())
        if comp > max
            max = comp
            maxname = item
        end
    end
    maxname
end

bestmatch("Akhilleus", namelist)